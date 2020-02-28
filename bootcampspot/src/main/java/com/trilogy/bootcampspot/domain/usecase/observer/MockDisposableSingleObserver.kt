package com.trilogy.bootcampspot.domain.usecase.observer

import io.reactivex.observers.DisposableSingleObserver

open class MockDisposableSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onError(e: Throwable) {
        //do nothing
    }

    override fun onSuccess(t: T) {
        //do nothing
    }

}