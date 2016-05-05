package com.drug.platform.dao;

import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/5.
 */
public interface DrugAmountDAO {

    /**
     * 保存全局(住院/门诊)药品用量统计结果
     *
     * @param drugAmountGlobals
     */
    public void saveGlobal(List<DrugAmountGlobal> drugAmountGlobals);

    /**
     * 保存科室药品用量统计结果
     *
     * @param drugAmountDepts
     */
    public void saveDept(List<DrugAmountDept> drugAmountDepts);

    /**
     * 保存医生药品用量统计结果
     *
     * @param drugAmountDoctors
     */
    public void saveDoctor(List<DrugAmountDoctor> drugAmountDoctors);
}
