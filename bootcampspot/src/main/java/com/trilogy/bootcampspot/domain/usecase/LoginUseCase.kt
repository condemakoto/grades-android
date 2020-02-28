package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class LoginUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val bcsRepository: BcsRepository
) :
    SingleUseCase<Boolean, LoginUseCase.LoginParams>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: LoginParams): Single<Boolean> {
        return bcsRepository.login(params.username, params.password)
    }

    data class LoginParams(val username: String, val password: String)
}