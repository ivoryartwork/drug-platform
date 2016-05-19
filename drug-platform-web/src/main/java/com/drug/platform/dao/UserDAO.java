package com.drug.platform.dao;

import com.drug.platform.model.AuthModule;
import com.drug.platform.model.User;
import com.drug.platform.model.UserAuthModules;

import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/4/18.
 */
public interface UserDAO {

    public User getByUserName(String userName);

    /**
     * 获取所有授权模块
     *
     * @return
     */
    public List<AuthModule> getAllAuthModules();

    /**
     * 保存新用户
     *
     * @param user
     */
    public void save(User user);

    /**
     * 获取用户列表
     *
     * @return
     */
    public List<User> list();

    /**
     * 保存用户授权模块
     *
     * @param userAuthModules
     */
    public void saveUserAuthModules(List<UserAuthModules> userAuthModules);

    /**
     * 根据用户username获取用户授权模块
     *
     * @param username
     * @return
     */
    public List<Map<String, Object>> getUserAuthModules(String username);

    public void delete(String username);

    public void deleteUserAuthModules(String username);

    public void update(User user);
}
