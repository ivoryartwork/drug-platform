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

    /**
     * 获取药品用量任务执行时间
     *
     * @return
     */
    public String getDrugAmountTaskExecTime();

    /**
     * 更新药品用量任务执行时间
     *
     * @param execTime
     */
    public void updateDrugAmountTaskExecTime(String execTime);

    /**
     * 获取处方金额任务执行时间
     *
     * @return
     */
    public String getParTaskExecTime();

    /**
     * 更新处方金额任务执行时间
     *
     * @param execTime
     */
    public void updateParTaskExecTime(String execTime);

    /**
     * 获取次均药费任务执行时间
     */
    public String getAverageDrugFeeTaskExecTime();

    /**
     * 更新次均药费任务执行时间
     */
    public void updateAverageDrugFeeTaskExecTime(String execTime);
}
