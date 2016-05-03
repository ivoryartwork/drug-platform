package com.drug.platform.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yaochao on 2016/5/3.
 */
public class StatisticsJob {

    private List<Task> tasks = new ArrayList<>();

    public void setTasks(Task... tasks) {
        this.tasks = Arrays.asList(tasks);
    }

    public void work() {
        for (Task task : tasks) {
            if (!task.isRunning()) {
                task.run();
            }
        }
    }
}
