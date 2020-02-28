package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.net.response.DashboardResponse
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetDashboardUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val bcsRepository: BcsRepository
) : SingleUseCase<DashboardResponse, Int>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(enrollmentId: Int): Single<DashboardResponse> {
        return bcsRepository.getDashboard(enrollmentId)
    }
}