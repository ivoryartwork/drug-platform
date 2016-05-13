package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.UserDAO;
import com.drug.platform.model.AuthModule;
import com.drug.platform.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public String getAllAuthModules() {
        List<AuthModule> authModules = userDAO.getAllAuthModules();
        return JSONObject.toJSONString(authModules);
    }
}
