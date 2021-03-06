package com.antaadama.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.antaadama.common.ResponseEnum;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.User;
import com.antaadama.repository.UserRepository;
import com.antaadama.service.IUserService;
import com.antaadama.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;



@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public ServerResponse userRegiste(User user) {
        try {
            if(null == user.getUserName() || null==user.getPassword())
                return  ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            if(null != userRepository.findByUserName(user.getUserName()))
                return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXSIT);

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.REGISTE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public ServerResponse getUserInfoByToken(String token) {
        User user = userRepository.findByToken(token);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(user.getId());
        userInfoVo.setName(user.getUserName());
        userInfoVo.setAvatar(user.getAvatar());
        userInfoVo.setIntroduction(user.getIntroduction());
        userInfoVo.setRoles(new ArrayList());
        return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(userInfoVo).code(20000);
    }

    @Override
    public ServerResponse updateUserInfo(User user) {
        try {
            Long id = user.getId();
            String introduction = user.getIntroduction();
            User cUser = userRepository.getOne(id);
            if(!StringUtils.isEmpty(introduction)){
                cUser.setIntroduction(introduction);
                userRepository.save(cUser);
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }


    @Override
    public ServerResponse updateUserPassword(JSONObject object) {
        try {
            Long id = object.getLong("id");
            User cUser = userRepository.getOne(id);

            String oldP = object.getString("oldP");
            String oldPStr = new BCryptPasswordEncoder().encode(oldP);
            String newP = object.getString("newP");
            String newPStr = new BCryptPasswordEncoder().encode(newP);

            if(! new BCryptPasswordEncoder().matches(oldP, cUser.getPassword())){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_FAILED);
            }else{
                cUser.setPassword(newPStr);
                userRepository.save(cUser);
                return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS).message("Change scucessfully");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
