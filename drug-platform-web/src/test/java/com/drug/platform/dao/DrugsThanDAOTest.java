package com.drug.platform.dao;

import com.drug.platform.model.DrugsThan;
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
 * Created by Yaochao on 2016/4/29.
 */
public class DrugsThanDAOTest extends BaseTestBean {

    @Resource
    private DrugsThanDAO drugsThanDAO;

    @Test
    public void testSave() throws Exception {
        List<DrugsThan> drugsThans = new ArrayList<>();
        DrugsThan drugsThan = new DrugsThan();
        drugsThan.setDeptCode("123");
        drugsThan.setxTotal(120);
        drugsThan.setyTotal(500);
        drugsThan.setFate(40);
        drugsThan.setTime(new Date());
        drugsThan.setChargeType("全费");
        drugsThan.setType("outp");
        drugsThans.add(drugsThan);

        DrugsThan drugsThan1 = new DrugsThan();
        drugsThan1.setDeptCode("1234");
        drugsThan1.setxTotal(1200);
        drugsThan1.setyTotal(5000);
        drugsThan1.setFate(40);
//        drugsThan1.setChargeType("全费");
        drugsThan1.setType("outp");
        drugsThan1.setTime(new Date());
        drugsThans.add(drugsThan1);
        drugsThanDAO.save(drugsThans);
    }

    @Test
    public void testStaDrugsThan() throws Exception {
//        QueryParams queryParams = new QueryParams();
//        queryParams.setType("inp");
//        queryParams.setChargeType("全费");
//        queryParams.setBeginDate(DateFormatUtils.parse("2015-01-01", DateFormatUtils.FORMAT_DATE));
//        queryParams.setEndDate(DateFormatUtils.parse("2016-01-01", DateFormatUtils.FORMAT_DATE));
//        queryParams.setDeptCode("210804");
//        Map<String, Object> objectMap = drugsThanDAO.staDrugsThan(queryParams);
//        System.out.println(objectMap.toString());
    }

    @Test
    public void testStaDeptDrugsThan() throws Exception {
        QueryParams queryParams = new QueryParams();
        queryParams.setType("inp");
        queryParams.setChargeType("全费");
        queryParams.setBeginDate(DateFormatUtils.parse("2015-01-01", DateFormatUtils.FORMAT_DATE));
        queryParams.setEndDate(DateFormatUtils.parse("2016-01-01", DateFormatUtils.FORMAT_DATE));
        List<Map<String, Object>> maps = drugsThanDAO.staDeptDrugsThan(queryParams);
        System.out.println(maps);
    }
}