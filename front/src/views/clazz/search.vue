<template>
  <div style="margin-top: 15px;width:50%;margin-left: 15px;">
    <el-input placeholder="Please enter content" v-model="input3" class="input-with-select">
      <el-select v-model="select" slot="prepend" placeholder="choose">
        <el-option label="student ID" value="1"></el-option>
        <el-option label="Name" value="2"></el-option>
      </el-select>
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
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
export default {
  data() {
    return {
      input1: '',
      input2: '',
      input3: '',
      select: ''
    }
  }
}
</script>
