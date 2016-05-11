package com.drug.platform.job;

import com.drug.platform.job.calculate.CalculatePar;
import com.drug.platform.model.Par;
import com.drug.platform.model.ParByDoctor;
import com.drug.platform.service.ParService;
import com.drug.platform.service.TaskConfigService;
import com.drug.platform.utils.DateFormatUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 * 统计处方金额排名
 */
public class ParTask implements Task {

    private Logger logger = Logger.getLogger(ParTask.class);

    private boolean isRunning = false;

    @Resource
    private ParService parService;

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
                String execTime = taskConfigService.getParTaskExecTime();
                String now = DateFormatUtils.format(new Date(), DateFormatUtils.FORMAT_DATE);
                Date execDate = DateFormatUtils.parse(execTime, DateFormatUtils.FORMAT_DATE);
                Date nowDate = DateFormatUtils.parse(now, DateFormatUtils.FORMAT_DATE);
                if (execDate.compareTo(nowDate) < 0) {
                    List<Par> pars = CalculatePar.calculate(execTime);
                    if (pars.size() > 0) {
                        parService.addParBatch(pars);
                    }
                    List<ParByDoctor> parByDoctors = CalculatePar.calculateByDoctor(execTime);
                    if (parByDoctors.size() > 0) {
                        parService.addParByDoctorBatch(parByDoctors);
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(execDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    taskConfigService.updateParTaskExecTime(DateFormatUtils.format(calendar.getTime(), DateFormatUtils.FORMAT_DATE));
                    logger.info(execTime + "：完成处方金额排名统计");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("处方金额排名统计出错:" + e.getMessage());
            e.printStackTrace();
        } finally {
            isRunning = false;
        }
    }
}
