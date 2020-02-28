package com.trilogy.bootcampspot.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.trilogy.bootcampspot.data.net.response.GradesResponse
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope

class GetGradesUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread, private val bcsRepository: BcsRepository
) :
    SingleUseCase<List<GradesResponse>, Int>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(enrollmentId: Int): Single<List<GradesResponse>> {
        return bcsRepository.getGrades(enrollmentId)
    }
}