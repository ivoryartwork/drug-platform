package com.drug.platform.dao;

import com.drug.platform.model.UserOptLog;

import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/20.
 */
public interface UserOptLogDAO {

    /**
     * 保存用户操作
     *
     * @param userOptLog
     */
    public void save(UserOptLog userOptLog);

    /**
     * 获取用户操作日志
     *
     * @param username
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<UserOptLog> getUserOptLogs(String username, Date beginDate, Date endDate);
}
