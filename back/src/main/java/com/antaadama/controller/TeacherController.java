package com.antaadama.controller;

import com.alibaba.fastjson.JSONObject;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.Teacher;
import com.antaadama.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addTeacher(@RequestBody Teacher teacher){
        return teacherService.addTeacher(teacher);
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateTeacher(@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse listTeacher(){
        return teacherService.listTeacher();
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public ServerResponse deleteTeacher(@PathVariable long id){
        return teacherService.deleteTeacher(id);
    }
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ServerResponse searchTeacher(@RequestBody JSONObject jsonObj){
        return teacherService.searchTeacher(jsonObj);
    }

}
