package com.drug.platform.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/9.
 * 特殊药品监控
 */
public interface SDMDAO {

    /**
     * 在院用量统计
     *
     * @return
     */
    public List<Map<String, Object>> drugStockInHospital();

    /**
     * 单个药品在院药品用量统计详细情况
     *
     * @param drugCode
     * @param drugSpec
     * @param firmId
     * @return
     */
    public List<Map<String, Object>> drugStockInHospitalDetail(String drugCode, String drugName, String drugSpec, String firmId);

    /**
     * 单病人用药统计
     *
     * @param patiendId
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String, Object>> spta(String patiendId, Date beginDate, Date endDate);
}
