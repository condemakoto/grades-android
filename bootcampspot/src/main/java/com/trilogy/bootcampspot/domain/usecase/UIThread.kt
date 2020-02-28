package com.trilogy.bootcampspot.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}