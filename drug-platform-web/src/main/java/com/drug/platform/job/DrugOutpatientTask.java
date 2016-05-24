package com.drug.platform.job;

import com.drug.platform.job.calculate.CalculateDrugOutpatient;
import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.service.DrugOutpatientService;
import com.drug.platform.service.TaskConfigService;
import com.drug.platform.utils.DateFormatUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 * 统计门诊此君药费比
 */
public class DrugOutpatientTask implements Task {

    private Logger logger = Logger.getLogger(DrugOutpatientTask.class);

    private boolean isRunning = false;

    @Resource
    private DrugOutpatientService drugOutpatientService;

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
                String execTime = taskConfigService.getDrugOutpatientTaskExecTime();
                String now = DateFormatUtils.format(new Date(), DateFormatUtils.FORMAT_DATE);
                Date execDate = DateFormatUtils.parse(execTime, DateFormatUtils.FORMAT_DATE);
                Date nowDate = DateFormatUtils.parse(now, DateFormatUtils.FORMAT_DATE);
                if (execDate.compareTo(nowDate) < 0) {
                    List<DrugOutpatient> drugOutpatients = CalculateDrugOutpatient.calculate(execTime);
                    if (drugOutpatients.size() > 0) {
                        drugOutpatientService.addDrugOutpatientBatch(drugOutpatients);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(execDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    taskConfigService.updateDrugOutpatientTaskExecTime(DateFormatUtils.format(calendar.getTime(), DateFormatUtils.FORMAT_DATE));
                    logger.info(execTime + "：完成门诊次均药费统计统计");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("门诊次均药费统计出错:" + e.getMessage());
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}
