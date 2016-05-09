package com.drug.platform.dao;

import com.github.pagehelper.PageHelper;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Yaochao on 2016/5/9.
 */
public class SDMDAOTest extends BaseTestBean {

    @Resource
    private SDMDAO sdmdao;

    @Test
    public void testDrugStockInHospital() throws Exception {
//        PageHelper.startPage(1, 2);
//        List<Map<String, Object>> list = sdmdao.drugStockInHospital();
//        System.out.println(list.get(1));
    }

    @Test
    public void testDrugStockInHospitalDetail() throws Exception {
        List<Map<String, Object>> list = sdmdao.drugStockInHospitalDetail("2302004IJ0", "盐酸布桂嗪注射液", "0.1g/2ml", "津金耀氨基");
        System.out.println(list.get(0));
    }
}