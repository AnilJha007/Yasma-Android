package com.talview.assignment.utils.schedulerProvider;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler io();

    Scheduler mainThread();
}
