package com.antaadama.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String userName;

    String password;

    String salt;

    String avatar;

    @Lob
    String introduction;
    String token;
    @OneToMany(cascade = {CascadeType.ALL} , fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    List<Role> roles;
}