package com.drug.platform.dao;

import com.drug.platform.model.UserOptLog;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/20.
 */
public class UserOptLogDAOTest extends BaseTestBean {

    @Resource
    private UserOptLogDAO userOptLogDAO;

    @Test
    public void testSave() throws Exception {
//        UserOptLog userOptLog = new UserOptLog();
//        userOptLog.setUsername("test");
//        userOptLog.setOptDate(new Date());
//        userOptLog.setOptDes("test----");
//        userOptLogDAO.save(userOptLog);
    }

    @Test
    public void testGetUserOptLogs() throws Exception {
//        List<UserOptLog> userOptLogs = userOptLogDAO.getUserOptLogs(null, null, null);
//        System.out.println(userOptLogs.size());
    }
}