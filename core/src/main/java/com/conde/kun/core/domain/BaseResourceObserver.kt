package com.conde.kun.core.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.conde.kun.core.error.BaseException

class BaseResourceObserver<V, T>(
    val observer: MediatorLiveData<V>,
    val source: LiveData<Resource<T>>,
    val callback: (d: Resource<T>) -> Unit
) :
    Observer<Resource<T>> {

    init {
        observer.addSource(source, this)
    }

    override fun onChanged(t: Resource<T>?) {
        if (t?.status != Status.LOADING) {
            observer.removeSource(source)
        }
        t?.let { callback(it) }
    }

}

class BaseResourceStatusObserver<V, T>(
    val observer: MediatorLiveData<V>,
    val source: LiveData<Resource<T>>,
    val successCallback: (d: T?) -> Unit,
    val errorCallback: (e: BaseException?) -> Unit,
    val progressCallback: (p: Int) -> Unit
) : Observer<Resource<T>> {

    init {
        observer.addSource(source, this)
    }

    override fun onChanged(t: Resource<T>?) {
        when (t?.status) {
            Status.LOADING -> progressCallback(t.progress)
            Status.SUCCESS -> {
                successCallback(t.data)
                observer.removeSource(source)
            }
            Status.ERROR -> {
                errorCallback(t.exception)
                observer.removeSource(source)
            }
        }
    }
}
