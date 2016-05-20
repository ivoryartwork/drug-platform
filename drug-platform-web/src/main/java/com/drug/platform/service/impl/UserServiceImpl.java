package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.UserDAO;
import com.drug.platform.dao.UserOptLogDAO;
import com.drug.platform.model.AuthModule;
import com.drug.platform.model.User;
import com.drug.platform.model.UserAuthModules;
import com.drug.platform.model.UserOptLog;
import com.drug.platform.service.UserService;
import com.drug.platform.utils.DateFormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Resource
    private UserOptLogDAO userOptLogDAO;

    @Override
    public User getByUserName(String username) {
        return userDAO.getByUserName(username);
    }

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
        user.setPassword(object.getString("password"));
        user.setNickname(object.getString("nickname"));
        user.setPhoneNum(object.getLong("phoneNum"));
        user.setEmail(object.getString("email"));
        user.setDeptCode(object.getString("deptCode"));
        user.setDeptName(object.getString("deptName"));
        user.setAuthUser(object.getString("authUser"));
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

    @Override
    public String userList() {
        List<User> userList = userDAO.list();
        JSONArray users = new JSONArray();
        for (User u : userList) {
            JSONObject user = new JSONObject();
            user.put("username", u.getUsername());
            user.put("nickname", u.getNickname());
            user.put("deptName", u.getDeptName());
            user.put("authUser", u.getAuthUser());
            user.put("phoneNum", u.getPhoneNum());
            user.put("email", u.getEmail());
            List<Map<String, Object>> modules = userDAO.getUserAuthModules(u.getUsername());
            String modulesName = "";
            for (Map<String, Object> map : modules) {
                modulesName += "[" + map.get("MODULENAME").toString() + "]";
            }
            user.put("modules", modulesName);
            users.add(user);
        }
        return users.toJSONString();
    }

    @Override
    public String userInfo(String username) {
        User user = userDAO.getByUserName(username);
        JSONObject userInfo = new JSONObject();
        userInfo.put("username", user.getUsername());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("password", user.getPassword());
        userInfo.put("deptName", user.getDeptName());
        userInfo.put("deptCode", user.getDeptCode());
        userInfo.put("authUser", user.getAuthUser());
        userInfo.put("phoneNum", user.getPhoneNum());
        userInfo.put("email", user.getEmail());
        List<Map<String, Object>> map = userDAO.getUserAuthModules(user.getUsername());
        JSONArray modules = (JSONArray) JSONArray.toJSON(map);
        userInfo.put("modules", modules);
        return userInfo.toJSONString();
    }

    @Override
    public void updateUser(String userData) throws Exception {
        JSONObject object = JSONObject.parseObject(userData);
        String userName = object.getString("username");
        User user = new User();
        user.setUsername(userName);
        user.setPassword(object.getString("password"));
        user.setNickname(object.getString("nickname"));
        user.setPhoneNum(object.getLong("phoneNum"));
        user.setEmail(object.getString("email"));
        user.setDeptCode(object.getString("deptCode"));
        user.setDeptName(object.getString("deptName"));
        userDAO.update(user);
        userDAO.deleteUserAuthModules(userName);
        JSONArray modulesCode = object.getJSONArray("modulesCode");
        List<UserAuthModules> userAuthModulesList = new ArrayList<>();
        for (int i = 0; i < modulesCode.size(); i++) {
            UserAuthModules userAuthModules = new UserAuthModules();
            userAuthModules.setModuleCode(modulesCode.getIntValue(i));
            userAuthModules.setUsername(userName);
            userAuthModulesList.add(userAuthModules);
        }
        userDAO.saveUserAuthModules(userAuthModulesList);
    }

    @Override
    public String userOptLogList(String username, Date beginDate, Date endDate, int page) {
        PageHelper.startPage(page, 15);
        List<UserOptLog> userOptLogs = userOptLogDAO.getUserOptLogs(username, beginDate, endDate);
        int offset = (page - 1) * 15 + 1;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < userOptLogs.size(); i++) {
            JSONObject object = new JSONObject();
            UserOptLog userOptLog = userOptLogs.get(i);
            object.put("num", offset + i);
            object.put("username", userOptLog.getUsername());
            object.put("optDes", userOptLog.getOptDes());
            object.put("optDate", DateFormatUtils.format(userOptLog.getOptDate(), DateFormatUtils.FORMAT_TIMESTAMP));
            jsonArray.add(object);
        }
        JSONObject result = new JSONObject();
        result.put("totalPages", ((Page) userOptLogs).getPages());
        result.put("pageData", jsonArray);
        return result.toJSONString();
    }
}
