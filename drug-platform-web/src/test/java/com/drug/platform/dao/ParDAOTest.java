package com.drug.platform.dao;

import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;
import com.drug.platform.utils.DateFormatUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/11.
 */
public class ParDAOTest extends BaseTestBean {

    @Resource
    private ParDAO parDAO;

    @Test
    public void testSave() throws Exception {
//        List<Par> pars = new ArrayList<>();
//        Par par = new Par();
//        par.setTotal(12);
//        par.setDeptName("23");
//        par.setPatientName("21");
//        par.setDoctor("2");
//        par.setRcptNo("23");
//        par.setPatientId("12");
//        par.setVisitDate(new Date());
//        par.setVisitNo("32");
//
//        Par par1 = new Par();
//        par1.setTotal(12);
//        par1.setDeptName("23");
//        par1.setPatientName("21");
//        par1.setDoctor("2");
//        par1.setRcptNo("23");
//        par1.setPatientId("12");
//        par1.setVisitDate(new Date());
//        par1.setVisitNo("32");
//
//        pars.add(par);
//        pars.add(par1);
//        parDAO.save(pars);
    }

    @Test
    public void testSaveParByDoctor() throws Exception {
//        List<ParByDoctor> parByDoctors = new ArrayList<>();
//        ParByDoctor parByDoctor = new ParByDoctor();
//        parByDoctor.setDoctor("12");
//        parByDoctor.setDeptName("12");
//        parByDoctor.setTotal(123);
//
//        ParByDoctor parByDoctor1 = new ParByDoctor();
//        parByDoctor1.setDoctor("12");
//        parByDoctor1.setDeptName("12");
//        parByDoctor1.setTotal(123);
//
//        parByDoctors.add(parByDoctor);
//        parByDoctors.add(parByDoctor1);
//        parDAO.saveParByDoctor(parByDoctors);
    }

    @Test
    public void testGetParListByTimeRange() throws Exception {
//        Date beginDate = DateFormatUtils.parse("2010-01-01 00:00:00", DateFormatUtils.FORMAT_TIMESTAMP);
//        Date endDate = DateFormatUtils.parse("2016-01-01 23:59:59", DateFormatUtils.FORMAT_TIMESTAMP);
//        List<Par> pars = parDAO.getParListByTimeRange(beginDate, endDate);
//        System.out.println(pars.size());
    }
}