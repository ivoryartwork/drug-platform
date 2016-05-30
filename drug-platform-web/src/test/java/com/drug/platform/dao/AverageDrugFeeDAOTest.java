package com.drug.platform.dao;

import com.drug.platform.model.AverageDrugFee;
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
public class AverageDrugFeeDAOTest extends BaseTestBean {

    @Resource
    private AverageDrugFeeDAO averageDrugFeeDAO;

    @Test
    public void testSave() throws Exception {
        List<AverageDrugFee> averageDrugFees = new ArrayList<>();
        AverageDrugFee averageDrugFee = new AverageDrugFee();
        averageDrugFee.setDeptCode("test");
        averageDrugFee.setChargeType("全包");
        averageDrugFee.setVisits(1);
        averageDrugFee.setTotalCosts(123);
        averageDrugFee.setTime(new Date());

        AverageDrugFee averageDrugFee1 = new AverageDrugFee();
        averageDrugFee1.setDeptCode("test");
        averageDrugFee1.setChargeType("全包");
        averageDrugFee1.setVisits(1);
        averageDrugFee1.setTotalCosts(123);
        averageDrugFee1.setTime(new Date());

        averageDrugFees.add(averageDrugFee);
        averageDrugFees.add(averageDrugFee1);
        averageDrugFeeDAO.save(averageDrugFees);
    }

    @Test
    public void testStaDrugOutpatientGlobal() throws Exception {
        QueryParams queryParams = new QueryParams();
        queryParams.setBeginDate(DateFormatUtils.parse("2010-01-01", "yyyy-MM-dd"));
        queryParams.setEndDate(DateFormatUtils.parse("2010-04-01", "yyyy-MM-dd"));
        queryParams.setChargeType("军队医改te");
        Map<String, Object> map = averageDrugFeeDAO.staAverageDrugFeeGlobal(queryParams);
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