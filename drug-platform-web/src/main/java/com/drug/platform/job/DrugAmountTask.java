package com.drug.platform.job;

import com.drug.platform.job.calculate.CalculateDrugAmount;
import com.drug.platform.job.calculate.CalculateDrugsThan;
import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.model.DrugsThan;
import com.drug.platform.service.DrugAmountService;
import com.drug.platform.service.DrugsThanService;
import com.drug.platform.service.TaskConfigService;
import com.drug.platform.utils.DateFormatUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 * 统计药费比任务
 */
public class DrugAmountTask implements Task {

    private Logger logger = Logger.getLogger(DrugAmountTask.class);

    private boolean isRunning = false;

    @Resource
    private DrugAmountService drugAmountService;

    @Resource
    private TaskConfigService taskConfigService;

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        isRunning = true;
        try {
            while (true) {
                String execTime = taskConfigService.getDrugAmountTaskExecTime();
                String now = DateFormatUtils.format(new Date(), DateFormatUtils.FORMAT_DATE);
                Date execDate = DateFormatUtils.parse(execTime, DateFormatUtils.FORMAT_DATE);
                Date nowDate = DateFormatUtils.parse(now, DateFormatUtils.FORMAT_DATE);
                if (execDate.compareTo(nowDate) < 0) {
                    List<DrugAmountGlobal> drugAmountGlobals = CalculateDrugAmount.calculateGlobal(execTime);
                    if (drugAmountGlobals.size() > 0) {
                        drugAmountService.addDrugAmountGlobalBatch(drugAmountGlobals);
                    }
                    List<DrugAmountDept> drugAmountDepts = CalculateDrugAmount.calculateDept(execTime);
                    if (drugAmountDepts.size() > 0) {
                        drugAmountService.addDrugAmountDeptBatch(drugAmountDepts);
                    }
                    List<DrugAmountDoctor> drugAmountDoctors = CalculateDrugAmount.calculateDoctor(execTime);
                    if (drugAmountDoctors.size() > 0) {
                        drugAmountService.addDrugAmountDoctorBatch(drugAmountDoctors);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(execDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    taskConfigService.updateDrugAmountTaskExecTime(DateFormatUtils.format(calendar.getTime(), DateFormatUtils.FORMAT_DATE));
                    logger.info(execTime + "：完成药品用量统计");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("药品用量统计出错:" + e.getMessage());
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}
