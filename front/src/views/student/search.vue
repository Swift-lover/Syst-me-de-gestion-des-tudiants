<template>
  <div>
    <el-input placeholder="Please enter content" v-model="content" class="input-with-select"
    style="margin-top: 15px;width:50%;margin-left: 15px;">
      <el-select v-model="select" slot="prepend" placeholder="choose">
        <el-option label="student ID" value="1"></el-option>
        <el-option label="Name" value="2"></el-option>
      </el-select>
      <el-button slot="append" icon="el-icon-search" @click="handleSearch()"></el-button>
    </el-input>

    <el-table
        :data="tableData"
        border
        style="margin-top: 15px;width:90%;margin-left: 15px;">
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
      </el-table>
  </div>
</template>

<style>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>
<script type="text/javascript">
import {searchStudent} from '@/api/student'
export default {
  data() {
    return {
      tableData: [],
      content: '',
      select: ''
    }
  },
  methods: {
    handleSearch(){
      var content = this.content;
      var select = this.select;
      var data = {
        content:content,
        select:select
      }
      if(select == null || "" == select){
        this.$message({
          message: "Select query category",
          type: 'error'
        });
        return;
      }

      if(content == null || "" == content){
        this.$message({
          message: "The query content cannot be empty",
          type: 'error'
        });
        return;
      }
      searchStudent(data).then(
        response=>{
          this.tableData = [];
          if(response.data.length == 0){
            this.$message({
              message: "No result",
              type: 'warning'
            });
          }else{
            this.tableData = response.data;
          }

        }
      )
    }
  }
}
</script>
