package com.drug.platform.dao;

import com.drug.platform.model.TaskConfig;

/**
 * Created by Yaochao on 2016/5/3.
 */
public interface TaskConfigDAO {

    /**
     * 获取配置
     *
     * @param taskId
     * @param itemName
     * @return
     */
    public String getItem(String taskId, String itemName);

    /**
     * 更新配置
     *
     * @param taskId
     * @param itemName
     * @param itemValue
     */
    public void updateItem(String taskId, String itemName, String itemValue);
}
