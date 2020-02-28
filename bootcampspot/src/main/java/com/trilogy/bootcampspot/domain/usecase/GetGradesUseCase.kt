package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.net.response.Grade
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetGradesUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread, private val bcsRepository: BcsRepository
) :
    SingleUseCase<List<Grade>, Int>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(enrollmentId: Int): Single<List<Grade>> {
        return bcsRepository.getGrades(enrollmentId)
    }
}