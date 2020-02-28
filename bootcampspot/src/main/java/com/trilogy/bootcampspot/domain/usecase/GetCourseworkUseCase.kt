package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.domain.model.CourseworkModel
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class GetCourseworkUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread, private val bcsRepository: BcsRepository
) : SingleUseCase<CourseworkModel, Int>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(assignmentId: Int): Single<CourseworkModel> {
        return bcsRepository.findCourseworkDetail(assignmentId)
    }
}