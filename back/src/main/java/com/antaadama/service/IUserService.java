package com.antaadama.service;

import com.alibaba.fastjson.JSONObject;
import com.antaadama.common.ServerResponse;
import com.antaadama.entity.User;

public interface IUserService {
    ServerResponse userRegiste(User user);
    ServerResponse getUserInfoByToken(String token);
    ServerResponse updateUserInfo(User user);
    ServerResponse updateUserPassword(JSONObject object);
}
