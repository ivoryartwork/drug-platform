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
}
