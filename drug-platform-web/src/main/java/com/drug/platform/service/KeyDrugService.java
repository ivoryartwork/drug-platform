package com.drug.platform.service;

/**
 * Created by Yaochao on 2016/5/30.
 */
public interface KeyDrugService {

    /**
     * 添加重点药品监控
     *
     * @param keyDrugData
     */
    public void addKeyDrug(String keyDrugData) throws Exception;

    /**
     * 获取药品监控结果列表
     *
     * @param type
     * @param pageNum
     * @return
     */
    public String getKeyDrugStaInfo(String type, int pageNum);

    /**
     * 删除重点药品
     *
     * @param drugCode
     */
    public void deleteKeyDrug(String drugCode) throws Exception;

    /**
     * 根据名称搜索重点药品统计信息
     *
     * @param drugName
     * @return
     */
    public String getKeyDrugStaInfoByName(String drugName);

    /**
     * 推荐同类药品
     *
     * @param drugCode
     * @return
     */
    public String recommendSimilarKeyDrugList(String drugCode);
}
