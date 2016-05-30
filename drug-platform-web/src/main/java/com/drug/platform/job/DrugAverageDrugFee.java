package com.drug.platform.job;

import com.drug.platform.job.calculate.CalculateAverageDrugFee;
import com.drug.platform.model.AverageDrugFee;
import com.drug.platform.service.AverageDrugFeeService;
import com.drug.platform.service.TaskConfigService;
import com.drug.platform.utils.DateFormatUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 * 统计次均药费
 */
public class DrugAverageDrugFee implements Task {

    private Logger logger = Logger.getLogger(DrugAverageDrugFee.class);

    private boolean isRunning = false;

    @Resource
    private AverageDrugFeeService averageDrugFeeService;

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
                String execTime = taskConfigService.getAverageDrugFeeTaskExecTime();
                String now = DateFormatUtils.format(new Date(), DateFormatUtils.FORMAT_DATE);
                Date execDate = DateFormatUtils.parse(execTime, DateFormatUtils.FORMAT_DATE);
                Date nowDate = DateFormatUtils.parse(now, DateFormatUtils.FORMAT_DATE);
                if (execDate.compareTo(nowDate) < 0) {
                    List<AverageDrugFee> averageDrugFees = CalculateAverageDrugFee.calculate(execTime);
                    if (averageDrugFees.size() > 0) {
                        averageDrugFeeService.addAverageDrugFeeBatch(averageDrugFees);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(execDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    taskConfigService.updateAverageDrugFeeTaskExecTime(DateFormatUtils.format(calendar.getTime(), DateFormatUtils.FORMAT_DATE));
                    logger.info(execTime + "：完成次均药费统计");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("次均药费统计出错:" + e.getMessage());
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}
