package com.drug.platform.dao;

import com.drug.platform.model.SimilarKeyDrug;

import java.util.List;

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
     * @param similarKeyDrugs
     */
    public void saveSimilarKeyDrug(List<SimilarKeyDrug> similarKeyDrugs);

    /**
     * @param type
     */
    public List<KeyDrug> getKeyDrugList(String type);

    /**
     * 删除重点药品
     *
     * @param drugCode
     */
    public void deleteKeyDrug(String drugCode);

    /**
     * 删除重点药品同类药品
     *
     * @param drugCode
     */
    public void deleteSimilarKeyDrug(String drugCode);

    /**
     * 根据名称获取重点药品信息
     *
     * @param drugName
     * @return
     */
    public List<KeyDrug> getKeyDrugListByName(String drugName);

    /**
     * 推荐同类药品
     *
     * @param drugCode
     * @return
     */
    public List<SimilarKeyDrug> recommendSimilarKeyDrugList(String drugCode);
}
