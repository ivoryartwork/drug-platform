package com.drug.platform.job;

/**
 * Created by Yaochao on 2016/5/3.
 * 统计药费比任务
 */
public class DrugsThanTask implements Task {

    private boolean isRunning = false;

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
        System.out.println("drugs than task run...");
//        isRunning = true;
    }
}
