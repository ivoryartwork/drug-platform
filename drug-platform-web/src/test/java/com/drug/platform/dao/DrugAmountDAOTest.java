package com.drug.platform.dao;

import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.model.QueryParams;
import com.drug.platform.utils.DateFormatUtils;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Yaochao on 2016/5/5.
 */
public class DrugAmountDAOTest extends BaseTestBean {

    @Resource
    private DrugAmountDAO drugAmountDAO;

    @Test
    public void testSaveGlobal() throws Exception {
//        List<DrugAmountGlobal> drugAmountGlobals = new ArrayList<>();
//        DrugAmountGlobal drugAmountGlobal = new DrugAmountGlobal();
//        drugAmountGlobal.setType("outp");
//        drugAmountGlobal.setTime(new Date());
//        drugAmountGlobal.setAmount(12);
//        drugAmountGlobal.setDrugCode("test");
//        drugAmountGlobal.setDrugName("test");
//        drugAmountGlobal.setDrugSpec("test");
//        drugAmountGlobal.setTotal(1232f);
//        drugAmountGlobal.setUnits("盒");
//        drugAmountGlobals.add(drugAmountGlobal);
//
//        DrugAmountGlobal drugAmountGlobal1 = new DrugAmountGlobal();
//        drugAmountGlobal1.setType("outp");
//        drugAmountGlobal1.setTime(new Date());
//        drugAmountGlobal1.setAmount(12);
//        drugAmountGlobal1.setDrugCode("test");
//        drugAmountGlobal1.setDrugName("test");
//        drugAmountGlobal1.setDrugSpec("test");
//        drugAmountGlobal1.setTotal(1232f);
//        drugAmountGlobal1.setUnits("盒");
//        drugAmountGlobals.add(drugAmountGlobal1);
//
//        drugAmountDAO.saveGlobal(drugAmountGlobals);
    }

    @Test
    public void testSaveDept() throws Exception {
//        List<DrugAmountDept> drugAmountDepts = new ArrayList<>();
//        DrugAmountDept drugAmountDept = new DrugAmountDept();
//        drugAmountDept.setDeptCode("12321321");
//        drugAmountDept.setTime(new Date());
//        drugAmountDept.setAmount(12);
//        drugAmountDept.setDrugCode("test");
//        drugAmountDept.setDrugName("test");
//        drugAmountDept.setDrugSpec("test");
//        drugAmountDept.setTotal(1232f);
//        drugAmountDept.setUnits("盒");
//        drugAmountDept.setType("inp");
//        drugAmountDepts.add(drugAmountDept);
//
//        DrugAmountDept drugAmountDept1 = new DrugAmountDept();
//        drugAmountDept1.setDeptCode("123123433");
//        drugAmountDept1.setTime(new Date());
//        drugAmountDept1.setAmount(12);
//        drugAmountDept1.setDrugCode("test");
//        drugAmountDept1.setDrugName("test");
//        drugAmountDept1.setDrugSpec("test");
//        drugAmountDept1.setTotal(1232f);
//        drugAmountDept1.setUnits("盒");
//        drugAmountDept1.setType("outp");
//        drugAmountDepts.add(drugAmountDept1);
//
//        drugAmountDAO.saveDept(drugAmountDepts);
    }

    @Test
    public void testSaveDoctor() throws Exception {
//        List<DrugAmountDoctor> drugAmountDoctors = new ArrayList<>();
//        DrugAmountDoctor drugAmountDoctor = new DrugAmountDoctor();
//        drugAmountDoctor.setDeptCode("12321321");
//        drugAmountDoctor.setDoctor("张三");
//        drugAmountDoctor.setTime(new Date());
//        drugAmountDoctor.setAmount(12);
//        drugAmountDoctor.setDrugCode("test");
//        drugAmountDoctor.setDrugName("test");
//        drugAmountDoctor.setDrugSpec("test");
//        drugAmountDoctor.setTotal(1232f);
//        drugAmountDoctor.setUnits("盒");
//        drugAmountDoctor.setType("inp");
//        drugAmountDoctor.setPatientAmount(1);
//        drugAmountDoctors.add(drugAmountDoctor);
//
//        DrugAmountDoctor drugAmountDoctor1 = new DrugAmountDoctor();
//        drugAmountDoctor1.setDeptCode("123123433");
//        drugAmountDoctor1.setDoctor("张三");
//        drugAmountDoctor1.setTime(new Date());
//        drugAmountDoctor1.setAmount(12);
//        drugAmountDoctor1.setDrugCode("test");
//        drugAmountDoctor1.setDrugName("test");
//        drugAmountDoctor1.setDrugSpec("test");
//        drugAmountDoctor1.setTotal(1232f);
//        drugAmountDoctor1.setUnits("盒");
//        drugAmountDoctor1.setType("outp");
//        drugAmountDoctor1.setPatientAmount(2);
//        drugAmountDoctors.add(drugAmountDoctor1);
//
//        drugAmountDAO.saveDoctor(drugAmountDoctors);
    }

    @Test
    public void testGetDrugAmountRankByDept() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setDrugCode("3012583WE0");
//        queryParams.setDrugSpec("6.5cm*10cm");
//        queryParams.setType("outp");
//        queryParams.setBeginDate(DateFormatUtils.parse("2010-10-10", "yyyy-MM-dd"));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-10-10", "yyyy-MM-dd"));
//        List<Map<String, Object>> mapList = drugAmountDAO.getDrugAmountRankByDept(queryParams);
//        System.out.println(mapList.size());
    }

    @Test
    public void testGetDrugAmountRankByDoctor() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setDrugCode("3012583WE0");
//        queryParams.setDrugSpec("6.5cm*10cm");
////        queryParams.setType("outp");
//        queryParams.setBeginDate(DateFormatUtils.parse("2010-10-10", "yyyy-MM-dd"));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-10-10", "yyyy-MM-dd"));
//        List<Map<String, Object>> mapList = drugAmountDAO.getDrugAmountRankByDoctor(queryParams);
//        System.out.println(mapList.get(0));
    }

    @Test
    public void testStaDrugAmountGlobal() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setDrugCode("1802009SL1");
//        queryParams.setDrugName("枸橼酸钾溶液");
//        queryParams.setDrugSpec("20%/10ml301制剂室");
////        queryParams.setType("inp");
////        queryParams.setChargeType("全费");
//        queryParams.setBeginDate(DateFormatUtils.parse("2010-01-01", DateFormatUtils.FORMAT_DATE));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-01-01", DateFormatUtils.FORMAT_DATE));
//        System.out.println(drugAmountDAO.staSingleDrugAmountGlobal(queryParams));
    }

    @Test
    public void testStaDrugAmountDeptList() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setDrugCode("1802009SL1");
//        queryParams.setDrugName("枸橼酸钾溶液");
//        queryParams.setDrugSpec("20%/10ml301制剂室");
////        queryParams.setType("inp");
////        queryParams.setChargeType("全费");
//        queryParams.setBeginDate(DateFormatUtils.parse("2010-01-01", DateFormatUtils.FORMAT_DATE));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-01-01", DateFormatUtils.FORMAT_DATE));
//        System.out.println(drugAmountDAO.staSingleDrugAmountDeptList(queryParams));
    }
}