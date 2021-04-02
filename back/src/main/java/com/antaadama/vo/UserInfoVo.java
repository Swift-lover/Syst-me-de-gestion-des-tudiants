package com.antaadama.vo;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;


@Data
public class UserInfoVo {
    Long id;
    String name;
    String avatar;
    @Lob
    String introduction;
    List roles;
}
