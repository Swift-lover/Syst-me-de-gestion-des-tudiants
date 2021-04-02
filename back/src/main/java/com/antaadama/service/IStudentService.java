package com.antaadama.service;

import com.alibaba.fastjson.JSONObject;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.Student;

public interface IStudentService {
    ServerResponse addStudent(Student student);
    ServerResponse listStudent();
    ServerResponse updateStudent(Student student);
    ServerResponse deleteStudent(Long id);
    ServerResponse searchStudent(JSONObject object);
}
