package com.drug.platform.dao;

import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.model.QueryParams;
import com.drug.platform.utils.DateFormatUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/24.
 */
public class DrugOutpatientDAOTest extends BaseTestBean {

    @Resource
    private DrugOutpatientDAO drugOutpatientDAO;

    @Test
    public void testSave() throws Exception {
        List<DrugOutpatient> drugOutpatients = new ArrayList<>();
        DrugOutpatient drugOutpatient = new DrugOutpatient();
        drugOutpatient.setDeptCode("test");
        drugOutpatient.setChargeType("全包");
        drugOutpatient.setVisits(1);
        drugOutpatient.setTotalCosts(123);
        drugOutpatient.setTime(new Date());

        DrugOutpatient drugOutpatient1 = new DrugOutpatient();
        drugOutpatient1.setDeptCode("test");
        drugOutpatient1.setChargeType("全包");
        drugOutpatient1.setVisits(1);
        drugOutpatient1.setTotalCosts(123);
        drugOutpatient1.setTime(new Date());

        drugOutpatients.add(drugOutpatient);
        drugOutpatients.add(drugOutpatient1);
        drugOutpatientDAO.save(drugOutpatients);
    }

    @Test
    public void testStaDrugOutpatientGlobal() throws Exception {
        QueryParams queryParams = new QueryParams();
        queryParams.setBeginDate(DateFormatUtils.parse("2010-01-01", "yyyy-MM-dd"));
        queryParams.setEndDate(DateFormatUtils.parse("2010-04-01", "yyyy-MM-dd"));
        queryParams.setChargeType("军队医改te");
        Map<String, Object> map = drugOutpatientDAO.staDrugOutpatientGlobal(queryParams);
        System.out.println(map);
    }

    @Test
    public void testStaDrugOutpatientByDept() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setBeginDate(DateFormatUtils.parse("2010-10-10", "yyyy-MM-dd"));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-10-10", "yyyy-MM-dd"));
////        queryParams.setChargeType("军队医改te");
//        List<Map<String, Object>> mapList = drugOutpatientDAO.staDrugOutpatientByDept(queryParams);
//        System.out.println(mapList);
    }
}