package com.antaadama.vo;

import lombok.Data;


@Data
public class ClazzInfoVo {
    Long id;
    String grade;
    String clazz;
    String headTeacher;
    Integer totalStudent;
    Integer currentTotalStudent;
}
