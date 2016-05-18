package com.drug.platform.dao;

import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Yaochao on 2016/5/18.
 */
public class PatientDAOTest extends BaseTestBean{

    @Resource
    private PatientDAO patientDAO;

    @Test
    public void testGetPatientInfo() throws Exception {
        System.out.println(patientDAO.getPatientInfo("10613104"));
    }
}