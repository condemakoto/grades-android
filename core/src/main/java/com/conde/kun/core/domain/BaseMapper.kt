package com.conde.kun.core.domain

abstract class BaseMapper<I, O> {

    abstract fun map(input: I): O

    fun mapAll(input: Collection<I>?): ArrayList<O> {
        val list = ArrayList<O>()
        input?.forEach {
            list.add(map(it))
        }
        return list
    }
}