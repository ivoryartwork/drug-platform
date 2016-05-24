package com.drug.platform.dao;

import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.model.QueryParams;

import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/24.
 */
public interface DrugOutpatientDAO {

    /**
     * 批量保存门诊次均药费
     *
     * @param drugsOutpatients
     */
    public void save(List<DrugOutpatient> drugsOutpatients);

    /**
     * 统计全局门诊次均药费
     *
     * @param queryParams
     * @return
     */
    public Map<String, Object> staDrugOutpatientGlobal(QueryParams queryParams);

    /**
     * 统计各科室门诊次均药费
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> staDrugOutpatientByDept(QueryParams queryParams);
}
