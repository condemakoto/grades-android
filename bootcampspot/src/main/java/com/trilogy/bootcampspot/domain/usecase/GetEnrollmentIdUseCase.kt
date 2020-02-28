package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetEnrollmentIdUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread, private val bcsRepository: BcsRepository
) : SingleUseCase<Int, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<Int> {
        return bcsRepository.getEnrollmentId()
    }
}