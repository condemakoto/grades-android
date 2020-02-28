package com.trilogy.bootcampspot.domain.usecase;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class JobExecutor implements ThreadExecutor {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }

}
