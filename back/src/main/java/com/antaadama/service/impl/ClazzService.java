package com.antaadama.service.impl;

import com.antaadama.common.ResponseEnum;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.Clazz;
import com.antaadama.entity.Student;
import com.antaadama.repository.ClazzRepository;
import com.antaadama.repository.StudentRespository;
import com.antaadama.service.IClazzService;
import com.antaadama.vo.ClazzInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClazzService implements IClazzService {
    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    StudentRespository studentRespository;

    @Override
    public ServerResponse addClazz(Clazz clazz) {
        try {
            String grade = clazz.getGrade();
            String clazzName = clazz.getClazz();
            if(StringUtils.isEmpty(grade)
            || StringUtils.isEmpty(clazzName))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            if(null != clazzRepository.findByGradeAndClazz(grade, clazzName))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.CLAZZ_EXSIT);
            clazzRepository.save(clazz);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse listAllClazz() {
        try {
            List<Clazz> clazzes = clazzRepository.findAll();
            if(null == clazzes)
                return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(new ArrayList<>());
            ArrayList<ClazzInfoVo> clazzInfoVos = new ArrayList<>();
            clazzes.forEach(item -> {
                ClazzInfoVo clazzInfoVo = new ClazzInfoVo();
                clazzInfoVo.setId(item.getId());
                clazzInfoVo.setGrade(item.getGrade());
                clazzInfoVo.setClazz(item.getClazz());
                clazzInfoVo.setHeadTeacher(item.getHeadTeacher());
                clazzInfoVo.setTotalStudent(item.getTotalStudent());
                clazzInfoVo.setCurrentTotalStudent(studentRespository.findByGradeAndClazz(item.getGrade(),
                        item.getClazz()).size());
                clazzInfoVos.add(clazzInfoVo);
            });
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzInfoVos);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse updateClazz(Clazz clazz) {
        try {
            Clazz oldClazz = clazzRepository.findById(clazz.getId()).get();
            oldClazz.setGrade(clazz.getGrade());
            oldClazz.setClazz(clazz.getClazz());
            oldClazz.setHeadTeacher(clazz.getHeadTeacher());
            oldClazz.setCurrentTotalStudent(clazz.getCurrentTotalStudent());
            clazzRepository.save(oldClazz);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse deleteClazz(Long id) {
        try {

            Clazz clazz = clazzRepository.getOne(id);
            List<Student> students =
                    studentRespository.findByGradeAndClazz(clazz.getGrade(), clazz.getClazz());
            if(students.size() > 0)
                return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_FIALED);
            clazzRepository.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        }catch (Exception e){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse getAllGrade() {
        try {
            List<String> grades = clazzRepository.selectGrades();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(grades);
        }catch (Exception e ) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse getAllClazz(String grade) {
        try {
            if(StringUtils.isEmpty(grade))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            List<String> clazzs = clazzRepository.selectClazzs(grade);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzs);
        }catch (Exception e ) {
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
