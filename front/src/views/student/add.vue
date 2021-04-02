<template>
  <el-form ref="form" :model="form" label-width="80px" style="width:50%;margin-top:20px" >
    <el-form-item label="Name">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="Age">
      <el-input v-model="form.age"></el-input>
    </el-form-item>
    <el-form-item label="Gender">
      <el-radio-group v-model="form.sex">
        <el-radio label="1" value="1">Male</el-radio>
        <el-radio label="0" value="0">Female</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="student ID">
      <el-input v-model="form.num"></el-input>
    </el-form-item>
    <el-form-item label="Grade">
      <el-select v-model="grade" placeholder="choosegrade">
        <el-option :label = item :value=item v-for="(item, i) in gradeList"></el-option>
      </el-select>
    </el-form-item>
    <el-collapse-transition>
      <el-form-item label="Class" v-show="classVisible">
        <el-select v-model="form.clazz" placeholder="chooseclass">
          <el-option :label=item :value=item v-for="(item, i) in clazzList"></el-option>
        </el-select>
      </el-form-item>
    </el-collapse-transition>
    <el-form-item label="Adress">
      <el-input v-model="form.address"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">Add</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import { getGrades, getClazzs } from '@/api/clazz';
import { addStudent } from '@/api/student';
  export default {
    data() {
      return {
        classVisible:false,
        gradeList: [],
        clazzList: [],
        grade: '',
        form: {
          name: '',
          age: '',
          sex: '',
          num: '',
          clazz: '',
          address: ''
        }
      }
    },
    mounted: function(){

      getGrades().then(
        response => {
          this.gradeList = response.data;
        }
      )
    },
    methods: {
      onSubmit() {

        var name = this.form.name;
        var age = this.form.age;
        var sex = this.form.sex;
        var num = this.form.num;
        var grade = this.grade;
        var clazz = this.form.clazz;
        var address = this.form.address;
        if(null === name || "" === name ||
           null === age || "" === age ||
           null === sex || "" === sex ||
           null === num || "" === num ||
           null === grade || "" === grade ||
           null === clazz || "" === clazz ||
           null === address || "" === address
         ){
           this.$message({
             message: "Please fill in the complete information",
             type: "error"
           })
         }else{
           var data = {
             name:name,
             age:age,
             sex:sex,
             num:num,
             grade:grade,
             clazz:clazz,
             address:address
           }
           addStudent(data).then(
             response=>{
               this.$message({
                 message: response.message,
                 type: "success"
               })
               this.form.name = "";
               this.form.age = "";
               this.form.sex = "";
               this.form.num = "";
               // this.grade = "";
               this.form.clazz = "";
               this.form.address = "";
             }
           )
         }
      }
    },
    watch:{
      grade: {
        handler: function(newVal, oldVal){
          if(newVal != oldVal){
            getClazzs(newVal).then(
              response => {
                this.clazzList = response.data
              }
            )
            this.classVisible = true
          }
        },
        deep: true
      }
    }
  }
</script>
