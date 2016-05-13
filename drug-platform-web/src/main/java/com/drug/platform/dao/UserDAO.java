package com.drug.platform.dao;

import com.drug.platform.model.AuthModule;
import com.drug.platform.model.User;

import java.util.List;

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
}
