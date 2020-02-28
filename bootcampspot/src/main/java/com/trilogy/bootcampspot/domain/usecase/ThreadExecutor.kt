package com.trilogy.bootcampspot.domain.usecase

import io.reactivex.Scheduler

interface ThreadExecutor {
    fun getScheduler(): Scheduler
}