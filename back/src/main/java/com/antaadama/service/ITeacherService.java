package com.antaadama.service;

import com.alibaba.fastjson.JSONObject;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.Teacher;

public interface ITeacherService {
    ServerResponse addTeacher(Teacher teacher);
    ServerResponse updateTeacher(Teacher teacher);
    ServerResponse listTeacher();
    ServerResponse deleteTeacher(Long id);
    ServerResponse searchTeacher(JSONObject obj);
}
