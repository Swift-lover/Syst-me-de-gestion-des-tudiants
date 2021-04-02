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
        label="student ID"
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
            {{ scope.row.sex|convertSex }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Grade"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.grade }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Class"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.clazz }}
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        label="Adress"
        width=""
        align="center">
          <template slot-scope="scope">
            {{ scope.row.address }}
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
        <el-form-item label="student ID" :label-width="formLabelWidth">
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
        <el-form-item label="Grade" :label-width="formLabelWidth">
          <el-select v-model="formGrade" placeholder="choosegrade">
            <el-option :label = item :value=item v-for="(item, i) in gradeList"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Class" :label-width="formLabelWidth" v-show="clazzVisible">
          <el-select v-model="form.clazz" placeholder="chooseclass">
            <el-option :label=item :value=item v-for="(item, i) in clazzList"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Adress" :label-width="formLabelWidth">
          <el-input v-model="form.address" autocomplete="off"></el-input>
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
import { getGrades, getClazzs } from '@/api/clazz';
import { listStudent, updateStudent, deleteStudent } from '@/api/student';

export default {
  data () {
    return {
      formLabelWidth:'',
      dialogFormVisible: false,
      clazzVisible: false,
      tableData: [],
      gradeList:[],
      clazzList: [],
      formGrade:'',
      formsex: 1,
      form: {
        index: '',
        id: '',
        num: '',
        name: '',
        age: '',
        clazz: '',
        address: ''
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
        this.form.clazz = row.clazz
        this.form.address = row.address
        getGrades().then(
          response => {
            this.gradeList = response.data
          }
        )
        this.formsex = row.sex
        this.formGrade = row.grade
        this.dialogFormVisible = true;

      },
      handleDelete(index, row) {
  
        var id = row.id;
        this.$confirm('Delete, are u sure?', 'Warning', {
        confirmButtonText: 'Valide',
        cancelButtonText: 'Cancel',
        type: 'warning'
        }).then(() => {
          deleteStudent(id).then(
            response => {
              this.tableData.splice(index, 1);
              this.$message({
                  message: response.message,
                  type: 'success'
                });
            }
          )
         }).catch(() => {
           this.$message({
             type: 'info',
             message: 'Delete is canceled'
           });
         });

      },
      handleUpdateStudent(){
        var id = this.form.id;
        var age = this.form.age;
        var sex = this.formsex;
        var num = this.form.num;
        var name = this.form.name;
        var grade = this.formGrade;
        var clazz = this.form.clazz;
        var address = this.form.address;
        if(null === id || "" === id ||
        null === age || "" === age ||
        null === sex || "" === sex ||
        null === num || "" === num ||
        null === name || "" === name ||
        null === grade || "" === grade ||
        null === clazz || "" === clazz ||
        null === address || "" === address){
          this.$message({
            message: "Please fill in the complete information",
            type: "error"
          })
        }else{

          var data = {
            id: id,
            age: age,
            sex: sex,
            num: num,
            name: name,
            grade: grade,
            clazz: clazz,
            address: address,
          }
          updateStudent(data).then(


            response => {
              // var i = this.form.index;
              // this.tableData[i].age = age;
              // this.tableData[i].sex = sex;
              // this.tableData[i].num = num;
              // this.tableData[i].name = name;
              // this.tableData[i].grade = grade;
              // this.tableData[i].clazz = clazz;
              // this.tableData[i].address = address;
              // this.dialogFormVisible = false;
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
      loadData(){
        listStudent().then(
          response=>{
            this.tableData = response.data;
          }
        )
      }
  },
  mounted: function(){
    this.loadData()
  },
  filters: {
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
  },
  watch: {
    formGrade: {
      handler: function(newVal, oldVal){
        if(newVal != oldVal){
          getClazzs(newVal).then(
            response => {
              this.clazzList = response.data
            }
          )
          this.clazzVisible = true
        }
      },
      deep: true
    }
  }
}
</script>
