package com.drug.platform.service;

import com.drug.platform.model.AverageDrugFee;
import com.drug.platform.model.QueryParams;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/24.
 */
public interface AverageDrugFeeService {

    /**
     * 批量添加次均药费统计结果
     *
     * @param averageDrugFees
     */
    public void addAverageDrugFeeBatch(List<AverageDrugFee> averageDrugFees);

    /**
     * 获取全院门诊次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    public String getAverageDrugFeeGlobal(QueryParams queryParams);

    /**
     * 获取某科室门诊次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    public String getDrugOutpatientByDept(QueryParams queryParams);
}
