package com.drug.platform.dao;

import com.drug.platform.model.*;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取全院（住院/门诊）一段时间的药品用量
     *
     * @param queryParams
     * @return
     */
    public List<DrugAmount> getGlobalDrugAmountList(QueryParams queryParams);

    /**
     * 获取单个药品科室用量排名
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> getDrugAmountRankByDept(QueryParams queryParams);

    /**
     * 获取单个药品医师用量排名
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> getDrugAmountRankByDoctor(QueryParams queryParams);

    /**
     * 统计全局单品种药品总用量
     *
     * @param queryParams
     * @return
     */
    public Map<String, Object> staSingleDrugAmountGlobal(QueryParams queryParams);

    /**
     * 统计各科室单品种药品总用量
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> staSingleDrugAmountDeptList(QueryParams queryParams);
}
