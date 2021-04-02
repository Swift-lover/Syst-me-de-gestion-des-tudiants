package com.antaadama.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "tb_clazz")
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String grade;
    String clazz;
    String headTeacher;
    Integer totalStudent;
    Integer currentTotalStudent;
}
