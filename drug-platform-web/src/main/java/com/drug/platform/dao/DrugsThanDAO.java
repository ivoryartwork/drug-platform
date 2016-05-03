package com.drug.platform.dao;

import com.drug.platform.model.DrugsThan;

import java.util.List;

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
}
