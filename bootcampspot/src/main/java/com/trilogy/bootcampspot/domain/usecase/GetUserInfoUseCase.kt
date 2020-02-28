package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.net.response.MeResponse
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetUserInfoUseCase(threadExecutor: ThreadExecutor,
                         mPostExecutionThread: PostExecutionThread, val bcsRepository: BcsRepository) :
    SingleUseCase<MeResponse, Unit?>(threadExecutor, mPostExecutionThread) {


    override fun buildObservable(params: Unit?): Single<MeResponse> {
        return bcsRepository.getUserInfo()
    }
}