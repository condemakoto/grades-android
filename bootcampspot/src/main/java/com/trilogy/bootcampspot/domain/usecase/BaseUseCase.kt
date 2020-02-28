package com.trilogy.bootcampspot.domain.usecase

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.Executor

open abstract class BaseUseCase(val mThreadExecutor: Executor) {
    var mSubscription: Disposable = Disposables.empty()

    fun unsubscribe() {
        if (mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }
}