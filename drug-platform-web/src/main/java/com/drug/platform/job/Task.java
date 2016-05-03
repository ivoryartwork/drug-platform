package com.drug.platform.job;

/**
 * Created by Yaochao on 2016/5/3.
 */
public interface Task extends Runnable {

    public boolean isRunning();
}
