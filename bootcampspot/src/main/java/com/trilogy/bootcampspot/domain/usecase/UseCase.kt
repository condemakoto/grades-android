package com.trilogy.bootcampspot.domain.usecase

interface UseCase<I, Params> {
    fun execute(callback: I, params: Params)
    fun stop()
}
