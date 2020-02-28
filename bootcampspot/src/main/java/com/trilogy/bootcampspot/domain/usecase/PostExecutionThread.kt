package com.trilogy.bootcampspot.domain.usecase

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}