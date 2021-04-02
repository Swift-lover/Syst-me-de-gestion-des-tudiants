<template>
  <el-form ref="form" :model="form" label-width="100px" style="width:30%;margin-top:20px" >
    <el-form-item label="Grade">
      <el-input v-model="grade"></el-input>
    </el-form-item>
    <el-form-item label="Class">
      <el-input v-model="clazz"></el-input>
    </el-form-item>
    <el-form-item label="Teacher of Class">
      <el-input v-model="headTeacher"></el-input>
    </el-form-item>
    <el-form-item label="Maximum number of people">
      <el-input v-model="totalStudent"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">Add</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import {addClazz} from '@/api/clazz'
  export default {
    data() {
      return {
          grade: '',
          clazz: '',
          headTeacher: '',
          totalStudent: ''
      }
    },
    methods: {
      onSubmit() {
        var grade = this.grade;
        var clazz = this.clazz;
        var headTeacher = this.headTeacher;
        var totalStudent = this.totalStudent;
        if(null === grade || "" === grade
           || null === clazz || "" === clazz
           || null === headTeacher || "" === headTeacher
           || null === totalStudent || "" === totalStudent){
             this.$message({
                 message: "Please fill in the complete class information",
                 type: 'error'
               });
           }else{
             //Send new request
             var data = {
               grade: grade,
               clazz: clazz,
               headTeacher: headTeacher,
               totalStudent: totalStudent
             }
             addClazz(data).then(
               response => {
                 if(response.code === "3002"){
                   this.$message({
                     message: "Class already exists",
                     type: "error"
                   })
                 }
                 if(response.code === 20000){
                   this.$message({
                     message: response.message,
                     type: "success"
                   })
                   this.grade = ""
                   this.clazz = ""
                   this.headTeacher = ""
                   this.totalStudent = ""
                 }
               }
             )

           }
      }
    },
    // watch:{
    //   grade: {
    //     handler: function(newVal, oldVal){
    //       if(newVal != oldVal){
    //         this.classVisible = true
    //       }
    //     },
    //     deep: true
    //   }
    // }
  }
</script>
