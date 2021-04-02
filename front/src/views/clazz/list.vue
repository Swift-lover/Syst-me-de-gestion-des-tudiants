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
          label="Teacher of Class"
          width=""
          align="center">
            <template slot-scope="scope">
              {{ scope.row.headTeacher }}
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          label="Maximum number of people"
          width=""
          align="center">
            <template slot-scope="scope">
              {{ scope.row.totalStudent }}
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          label="Current number"
          width=""
          align="center">
            <template slot-scope="scope">
              {{ scope.row.currentTotalStudent }}
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

    <el-dialog title="Editclass information" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-input v-model="form.id" autocomplete="off" v-show="false"></el-input>
        <el-form-item label="Grade" :label-width="formLabelWidth">
          <el-input v-model="form.grade" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Class" :label-width="formLabelWidth">
          <el-input v-model="form.clazz" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Teacher of Class" :label-width="formLabelWidth">
          <el-input v-model="form.headTeacher" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Maximum number of people" :label-width="formLabelWidth">
          <el-input v-model="form.totalStudent" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="handleUpdateClazz()">Valide</el-button>
      </div>
    </el-dialog>
  </div>


</template>




<script type="text/javascript">
import { listClazz,updateClazz,deleteClazz } from '@/api/clazz'
export default {
  data () {
    return {
      dialogFormVisible: false,
      formLabelWidth:'100px',
      tableData: [],
      form: {
        index: '',
        id: '',
        grade: '',
        clazz: '',
        headTeacher: '',
        totalStudent: ''
      }
    }
  },
  methods: {
      loadData(){
        var response = listClazz().then(
          response => {
            this.tableData = response.data
          }
        )

      },
      handleEdit(index, row) {
        this.dialogFormVisible = true;
        this.form.index = index;
        var id = row.id;
        var grade = row.grade;
        var clazz = row.clazz;
        var headTeacher = row.headTeacher
        var totalStudent = row.totalStudent
        this.form.id = id;
        this.form.grade = grade;
        this.form.clazz = clazz;
        this.form.headTeacher = headTeacher;
        this.form.totalStudent = totalStudent;
      },
      handleDelete(index, row) {
        var id = row.id;
        this.$confirm('Delete, are u sure?', 'Warning', {
        confirmButtonText: 'Valide',
        cancelButtonText: 'Cancel',
        type: 'warning'
        }).then(() => {
          deleteClazz(id).then(
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
      handleUpdateClazz(){
        var id = this.form.id;
        var grade = this.form.grade;
        var clazz = this.form.clazz;
        var headTeacher = this.form.headTeacher
        var totalStudent = this.form.totalStudent
        if(id===null || id === ""
          || grade===null || grade === ""
          || clazz===null || clazz === ""
          || headTeacher===null || headTeacher === ""
          || totalStudent===null || totalStudent === ""
        ){
          this.$message({
              message: 'Please fill in information',
              type: 'warning'
            });
        }else{
          //Submit a modification request
          var data = {
            id:id,
            grade:grade,
            clazz:clazz,
            headTeacher:headTeacher,
            totalStudent:totalStudent
          }
          updateClazz(data).then(
            response => {
              var i = this.form.index;
              this.tableData[i].grade = grade;
              this.tableData[i].clazz = clazz;
              this.tableData[i].headTeacher = headTeacher;
              this.tableData[i].totalStudent = totalStudent;
              this.dialogFormVisible = false;
              this.$message({
                  message: response.message,
                  type: 'success'
                });
            }
          );
        }

      }
    },
    mounted: function() {
      this.loadData()
    }
}
</script>
