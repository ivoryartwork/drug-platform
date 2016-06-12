package com.drug.platform.service;

import com.alibaba.fastjson.JSONArray;
import com.drug.platform.model.DrugsThan;
import com.drug.platform.model.QueryParams;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 */
public interface DrugsThanService {

    /**
     * 批量插入药费比
     *
     * @param drugsThans
     */
    public void addDrugsThanBatch(List<DrugsThan> drugsThans);

    /**
     * 全局统计药费比
     *
     * @param queryParams
     * @return json格式
     */
    public String staGlobalDrugsThan(QueryParams queryParams);

    /**
     * 统计各科室药费比
     *
     * @param queryParams
     * @return json格式
     */
    public JSONArray staDeptDrugsThan(QueryParams queryParams);
}
