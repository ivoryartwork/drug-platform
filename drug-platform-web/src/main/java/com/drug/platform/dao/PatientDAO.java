package com.drug.platform.dao;

import java.util.Map;

/**
 * Created by Yaochao on 2016/5/18.
 */
public interface PatientDAO {

    /**
     * 根据患者id获取患者信息
     *
     * @param patientId
     * @return
     */
    public Map<String, Object> getPatientInfo(String patientId);
}
