package com.drug.platform.dao;

import com.drug.platform.model.DrugNameDict;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yaochao on 2016/5/5.
 */
public class DrugNameDictDAOTest extends BaseTestBean {

    @Resource
    private DrugNameDictDAO drugNameDictDAO;

    @Test
    public void testSearchByInputCode() throws Exception {
        List<String> drugNameDicts = drugNameDictDAO.searchByInputCode("%X%");
        System.out.println(drugNameDicts.size());
    }
}