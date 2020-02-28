package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.domain.model.EnrollmentInfo
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetEnrollmentInfoUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    val bcsRepository: BcsRepository
) : SingleUseCase<List<EnrollmentInfo>, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<List<EnrollmentInfo>> {
        return bcsRepository.listEnrollmentInfo()
    }
}