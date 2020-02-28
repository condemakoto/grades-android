package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.net.response.UserAccount
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetUserAccountUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread, private val repository: BcsRepository
) :
    SingleUseCase<UserAccount, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<UserAccount> {
        return repository.getUserAccount()
    }
}