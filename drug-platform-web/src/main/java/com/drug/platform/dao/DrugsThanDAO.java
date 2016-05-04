package com.drug.platform.dao;

import com.drug.platform.model.DrugsThan;
import com.drug.platform.model.QueryParams;

import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/4/29.
 */
public interface DrugsThanDAO {

    /**
     * 批量插入药费比
     *
     * @param drugsThans
     */
    public void save(List<DrugsThan> drugsThans);

    /**
     * 根据查询条件统计不同条件下的药费比
     *
     * @param queryParams
     * @return
     */
    public Map<String, Object> staDrugsThan(QueryParams queryParams);

    /**
     * 根据查询条件统计不同条件下各科室的药费比
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> staDeptDrugsThan(QueryParams queryParams);
}
