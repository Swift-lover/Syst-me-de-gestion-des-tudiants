<template>
  <el-form ref="form" :model="form" label-width="100px" style="width:30%;margin-top:20px" >
    <el-form-item label="old password">
      <el-input v-model="oldP" type="password"></el-input>
    </el-form-item>
    <el-form-item label="New Password">
      <el-input v-model="newP" type="password"></el-input>
    </el-form-item>
    <el-form-item label="New Password" >
      <el-input v-model="newP1" type="password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Submit</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import {updateUserPassword, logout} from '@/api/user'
  export default {
    data() {
      return {
          oldP: '',
          newP: '',
          newP1: ''
      }
    },
    methods: {
      onSubmit() {
        var oldP = this.oldP;
        var newP = this.newP;
        var id = this.$store.getters.id
        if(null === oldP || "" === oldP
           || null === newP || "" === newP){
             this.$message({
                 message: "Please fill in the complete information",
                 type: 'error'
               });
           }else{
             //Send new request
             var data = {
               id: id,
               oldP: oldP,
               newP: newP
             }
             updateUserPassword(data).then(
               response => {
                 if(response.code === 20000){
                   this.$message({
                     message: response.message,
                     type: "success"
                   })
                   this.oldP = ""
                   this.newP = ""
                   this.newP1 = ""
                   logout()
                 }
               }
             )

           }
      }
    },

  }
</script>
