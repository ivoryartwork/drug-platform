package com.drug.platform.dao;

import com.drug.platform.model.AverageDrugFee;
import com.drug.platform.model.QueryParams;

import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/24.
 */
public interface AverageDrugFeeDAO {

    /**
     * 批量保存次均药费
     *
     * @param averageDrugFees
     */
    public void save(List<AverageDrugFee> averageDrugFees);

    /**
     * 统计全局次均药费
     *
     * @param queryParams
     * @return
     */
    public Map<String, Object> staAverageDrugFeeGlobal(QueryParams queryParams);

    /**
     * 统计各科室门诊次均药费
     *
     * @param queryParams
     * @return
     */
    public List<Map<String, Object>> staDrugOutpatientByDept(QueryParams queryParams);
}
