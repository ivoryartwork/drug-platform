package com.drug.platform.service;

import com.drug.platform.model.DrugsThan;

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
}
