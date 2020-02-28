package com.conde.kun.core.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.conde.kun.core.error.BaseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseUseCase<T, P>(val coroutineScope: CoroutineScope) {

    lateinit var response: MutableLiveData<Resource<T>>

    fun execute(param: P): LiveData<Resource<T>> {
        response = MutableLiveData()
        response.postValue(Resource.loading(0))

        coroutineScope.launch {
            try {
                val data = getData(param)
                response.postValue(Resource.success(data))
            } catch (ex: Exception) {
                val exception: BaseException =
                if (ex is BaseException) {
                    ex
                } else {
                    BaseException()
                }
                response.postValue(Resource.error(exception))
            }
        }

        return response
    }

    protected abstract suspend fun getData(param: P): T

    protected fun postProgress(progress: Int) {
        response.postValue(Resource.loading(progress))
    }

}