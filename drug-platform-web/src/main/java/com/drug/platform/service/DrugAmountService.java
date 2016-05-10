package com.drug.platform.service;

import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.model.QueryParams;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/5.
 * 药品用量
 */
public interface DrugAmountService {

    /**
     * 保存全局统计结果
     *
     * @param drugAmountGlobals
     */
    public void addDrugAmountGlobalBatch(List<DrugAmountGlobal> drugAmountGlobals);

    /**
     * 保存科室统计结果
     *
     * @param drugAmountDepts
     */
    public void addDrugAmountDeptBatch(List<DrugAmountDept> drugAmountDepts);

    /**
     * 保存医生统计结果
     *
     * @param drugAmountDoctors
     */
    public void addDrugAmountDoctorBatch(List<DrugAmountDoctor> drugAmountDoctors);

    /**
     * 获取单个药品各科室用量排名
     *
     * @param queryParams
     * @return
     */
    public String getDrugAmountRankByDept(QueryParams queryParams);

    /**
     * 获取单个药品各医师用量排名
     *
     * @param queryParams
     * @return
     */
    public String getDrugAmountRankByDoctor(QueryParams queryParams);
}
