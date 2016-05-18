package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.UserDAO;
import com.drug.platform.model.AuthModule;
import com.drug.platform.model.User;
import com.drug.platform.model.UserAuthModules;
import com.drug.platform.service.UserService;
import com.drug.platform.utils.PasswordHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public int createUser(String userData) throws Exception {
        JSONObject object = JSONObject.parseObject(userData);
        String userName = object.getString("username");
        if (userDAO.getByUserName(userName) != null) {
            return 1;
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(PasswordHash.createHash(object.getString("password")));
        user.setNickname(object.getString("nickname"));
        user.setPhoneNum(object.getLong("phoneNum"));
        user.setEmail(object.getString("email"));
        user.setDeptCode(object.getString("deptCode"));
        user.setDeptName(object.getString("deptName"));
        userDAO.save(user);

        JSONArray modulesCode = object.getJSONArray("modulesCode");
        List<UserAuthModules> userAuthModulesList = new ArrayList<>();
        for (int i = 0; i < modulesCode.size(); i++) {
            UserAuthModules userAuthModules = new UserAuthModules();
            userAuthModules.setModuleCode(modulesCode.getIntValue(i));
            userAuthModules.setUsername(userName);
            userAuthModulesList.add(userAuthModules);
        }
        userDAO.saveUserAuthModules(userAuthModulesList);
        return 0;
    }

    @Override
    public void deleteUser(String username) {
        userDAO.delete(username);
        userDAO.deleteUserAuthModules(username);
    }
}
