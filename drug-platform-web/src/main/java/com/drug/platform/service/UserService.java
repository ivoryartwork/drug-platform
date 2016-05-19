package com.drug.platform.service;

import com.drug.platform.model.User;

/**
 * Created by Yaochao on 2016/5/13.
 */
public interface UserService {

    public User getByUserName(String username);

    public String getAllAuthModules();

    public int createUser(String userData) throws Exception;

    public void deleteUser(String username);

    public String userList();

    public String userInfo(String username);

    public void updateUser(String userData) throws Exception;
}
