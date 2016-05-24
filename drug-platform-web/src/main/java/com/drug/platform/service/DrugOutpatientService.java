package com.drug.platform.service;

import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.model.QueryParams;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/24.
 */
public interface DrugOutpatientService {

    /**
     * 批量添加门诊次均药费统计结果
     *
     * @param drugOutpatients
     */
    public void addDrugOutpatientBatch(List<DrugOutpatient> drugOutpatients);

    /**
     * 获取全院门诊次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    public String getDrugOutpatientGlobal(QueryParams queryParams);

    /**
     * 获取某科室次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    public String getDrugOutpatientByDept(QueryParams queryParams);
}
