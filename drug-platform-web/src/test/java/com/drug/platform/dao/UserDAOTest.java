package com.drug.platform.dao;

import com.drug.platform.model.UserAuthModules;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivoryartwork on 2016/4/28.
 */
public class UserDAOTest extends BaseTestBean {

    @Resource
    private UserDAO userDAO;

    @Test
    public void testSave() throws Exception {
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("stet");
//        user.setNickname("sdf");
//        user.setPhoneNum(123);
//        user.setEmail("22222@qq.com");
//        user.setDeptName("sdd");
//        user.setDeptCode("sdf");
//        userDAO.save(user);
    }


    @Test
    public void testList() throws Exception {
        System.out.println(userDAO.list());
    }

    @Test
    public void testSaveUserAuthModules() throws Exception {
        List<UserAuthModules> userAuthModulesList = new ArrayList<>();
        UserAuthModules userAuthModules = new UserAuthModules();
        userAuthModules.setUsername("test");
        userAuthModules.setModuleCode(1111);

        UserAuthModules userAuthModules1 = new UserAuthModules();
        userAuthModules1.setUsername("test");
        userAuthModules1.setModuleCode(1111);
        userAuthModulesList.add(userAuthModules);
        userAuthModulesList.add(userAuthModules1);
        userDAO.saveUserAuthModules(userAuthModulesList);
    }

    @Test
    public void testGetUserAuthModules() throws Exception {
        System.out.println(userDAO.getUserAuthModules("test"));
    }
}