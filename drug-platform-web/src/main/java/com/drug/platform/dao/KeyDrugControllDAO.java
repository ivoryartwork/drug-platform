package com.drug.platform.dao;

import com.drug.platform.model.KeyDrug;
import com.drug.platform.model.SimilarKeyDrug;

/**
 * Created by Yaochao on 2016/5/26.
 * 重点药品监控
 */
public interface KeyDrugControllDAO {

    /**
     * 保存药品信息
     *
     * @param keyDrug
     */
    public void saveKeyDrug(KeyDrug keyDrug);

    /**
     * 保存同类药品关系
     *
     * @param similarKeyDrug
     */
    public void saveSimilarKeyDrug(SimilarKeyDrug similarKeyDrug);
}
