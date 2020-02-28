package com.conde.kun.core.domain

import com.conde.kun.core.error.BaseException

data class Resource<out T>(val status: Status, val data: T? = null, val exception: BaseException? = null, val progress: Int = 0) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(exception: BaseException?): Resource<T> {
            return Resource(Status.ERROR, exception = exception)
        }

        fun <T> loading(progress: Int): Resource<T> {
            return Resource(Status.LOADING, progress = progress)
        }
    }
}