package com.drug.platform.service;

/**
 * Created by Yaochao on 2016/5/9.
 * 特殊药品监控
 */
public interface SDMService {

    /**
     * 全院药品总量
     *
     * @param page 当前页
     * @return
     */
    public String drugStockInHospital(int page);

    /**
     *
     * @param drugCode
     * @param drugName
     * @param drugSpec
     * @param firmId
     * @return
     */
    public String drugStockInHospitalDetail(String drugCode, String drugName, String drugSpec, String firmId);
}
