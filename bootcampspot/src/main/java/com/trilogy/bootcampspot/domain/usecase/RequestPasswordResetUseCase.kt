package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class RequestPasswordResetUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val repository: BcsRepository
) :
    SingleUseCase<Boolean, String>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(username: String): Single<Boolean> {
        return repository.requestPasswordReset(username)
    }
}