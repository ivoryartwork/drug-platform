package com.drug.platform.model;

/**
 * Created by Yaochao on 2016/5/3.
 */
public class TaskConfig {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 配置项名称
     */
    private String itemName;

    /**
     * 配置项值
     */
    private String itemValue;

    /**
     * 配置项描述
     */
    private String description;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
