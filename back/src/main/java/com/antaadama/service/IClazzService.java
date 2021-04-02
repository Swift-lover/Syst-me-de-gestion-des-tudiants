package com.antaadama.service;

import com.antaadama.common.ServerResponse;
import com.antaadama.entity.Clazz;


public interface IClazzService {
    ServerResponse addClazz(Clazz clazz);
    ServerResponse listAllClazz();
    ServerResponse deleteClazz(Long id);
    ServerResponse getAllGrade();
    ServerResponse getAllClazz(String grade);
    ServerResponse updateClazz(Clazz clazz);




}
