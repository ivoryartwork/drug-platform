package com.drug.platform.service;

/**
 * Created by Yaochao on 2016/5/3.
 */
public interface TaskConfigService {

    /**
     * 获取药占比任务执行时间
     *
     * @return
     */
    public String getDrugsThanTaskExecTime();

    /**
     * 更新药占比任务执行时间
     *
     * @param execTime
     */
    public void updateDrugsThanTaskExecTime(String execTime);
}
