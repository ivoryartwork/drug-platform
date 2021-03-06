package com.drug.platform.service.impl;

import com.drug.platform.dao.TaskConfigDAO;
import com.drug.platform.service.TaskConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Yaochao on 2016/5/3.
 */
@Service("taskConfigService")
public class TaskConfigServiceImpl implements TaskConfigService {

    @Resource
    private TaskConfigDAO taskConfigDAO;

    /**
     * 获取药占比任务执行时间
     *
     * @return
     */
    @Override
    public String getDrugsThanTaskExecTime() {
        return taskConfigDAO.getItem("drugsThan", "execTime");
    }

    /**
     * 更新药占比任务执行时间
     *
     * @param execTime
     */
    @Override
    public void updateDrugsThanTaskExecTime(String execTime) {
        taskConfigDAO.updateItem("drugsThan", "execTime", execTime);
    }

    /**
     * 获取药品用量任务执行时间
     *
     * @return
     */
    @Override
    public String getDrugAmountTaskExecTime() {
        return taskConfigDAO.getItem("drugAmount", "execTime");
    }

    /**
     * 更新药品用量任务执行时间
     *
     * @param execTime
     */
    @Override
    public void updateDrugAmountTaskExecTime(String execTime) {
        taskConfigDAO.updateItem("drugAmount", "execTime", execTime);
    }

    /**
     * 获取处方金额任务执行时间
     *
     * @return
     */
    @Override
    public String getParTaskExecTime() {
        return taskConfigDAO.getItem("par", "execTime");
    }

    /**
     * 更新处方金额任务执行时间
     *
     * @param execTime
     */
    @Override
    public void updateParTaskExecTime(String execTime) {
        taskConfigDAO.updateItem("par", "execTime", execTime);
    }

    /**
     * 获取次均药费任务执行时间
     */
    @Override
    public String getAverageDrugFeeTaskExecTime() {
        return taskConfigDAO.getItem("averageDrugFee", "execTime");
    }

    /**
     * 更新次均药费任务执行时间
     */
    @Override
    public void updateAverageDrugFeeTaskExecTime(String execTime) {
        taskConfigDAO.updateItem("averageDrugFee", "execTime", execTime);
    }
}
