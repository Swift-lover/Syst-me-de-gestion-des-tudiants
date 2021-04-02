<template>
<div class="">
  <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        label="Index"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
      </el-table-column>
      <el-table-column
        label="Job number"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.num }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Name"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Age"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.age }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Gender"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.sex | convertSex}}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Subject"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.course }}
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        label="Operate"
        align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog title="Edit student information" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-input v-model="form.index" autocomplete="off" v-show="false"></el-input>
        <el-input v-model="form.id" autocomplete="off" v-show="false"></el-input>
        <el-form-item label="Job number" :label-width="formLabelWidth">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Name" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Age" :label-width="formLabelWidth">
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Gender" :label-width="formLabelWidth">
          <el-radio-group v-model="formsex">
            <el-radio :label="1">Male</el-radio>
            <el-radio :label="0">Female</el-radio>
          </el-radio-group>
        </el-form-item>
        </el-form-item>
        <el-form-item label="Subject" :label-width="formLabelWidth">
          <el-input v-model="form.course" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleUpdateStudent()">Valide</el-button>
      </div>
    </el-dialog>
</div>
</template>




<script type="text/javascript">
import {listTeacher, updateTeacher, deleteTeacher} from '@/api/teacher'
export default {
  data () {
    return {
      formLabelWidth:'',
      sexRadio: '',
      tableData: [],
      dialogFormVisible: false,
      formsex: 0,
      form: {
        index: '',
        id: '',
        num: '',
        name: '',
        age: '',
        course: ''
      }

    }
  },
    methods: {
      handleEdit(index, row) {
        this.form.index = index;
        this.form.id = row.id
        this.form.age = row.age
        this.formsex = row.sex
        this.form.num = row.num
        this.form.name = row.name
        this.form.course = row.course
        this.dialogFormVisible = true;
      },
      handleDelete(index, row) {

        var id = row.id;
        this.$confirm('Delete, are u sure?', 'Warning', {
        confirmButtonText: 'Valide',
        cancelButtonText: 'Cancel',
        type: 'warning'
        }).then(() => {
          deleteTeacher(id).then(
            response => {
              this.tableData.splice(index, 1);
              this.$message({
                  message: response.message,
                  type: 'success'
                });
            }
          )
         }).catch((response) => {
           console.log(response);
           this.$message({
             type: 'info',
             message: 'Delete is canceled'
           });
         });
      },
      loadData(){
        listTeacher().then(
          response => {
            this.tableData = response.data;
          }
        )
      },
      handleUpdateStudent(){
        var id = this.form.id;
        var age = this.form.age;
        var sex = this.formsex;
        var num = this.form.num;
        var name = this.form.name;
        var course = this.form.course;
        var data = {
          id: id,
          age: age,
          sex: sex,
          num: num,
          name: name,
          course: course
        }

        updateTeacher(data).then(


          response => {
            this.dialogFormVisible = false;
            this.loadData()
            this.$message({
              message: response.message,
              type: "success"
            })
          }
        )

      }
    },
  mounted: function(){
    this.loadData();
  },
  filters:{
    convertSex(sex){
      switch(sex){
        case 1:
          return "Male"
          break
        case 0:
          return "Female"
          break
      }
    }
  }
}
</script>
