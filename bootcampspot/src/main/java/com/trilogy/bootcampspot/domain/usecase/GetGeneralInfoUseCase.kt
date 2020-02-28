package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.net.response.UserAccount
import com.trilogy.bootcampspot.domain.model.EnrollmentInfo
import com.trilogy.bootcampspot.domain.model.GeneralInfo
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetGeneralInfoUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val getUserAccountUseCase: GetUserAccountUseCase,
    private val getEnrollmentInfoUseCase: GetEnrollmentInfoUseCase
)

    : SingleUseCase<GeneralInfo, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<GeneralInfo> {

        return getUserAccountUseCase.buildObservable(null)
            .zipWith(
                getEnrollmentInfoUseCase.buildObservable(null),
                BiFunction<UserAccount, List<EnrollmentInfo>, GeneralInfo> { userAccount, enrollments ->
                    GeneralInfo(userAccount, enrollments)
                }
            )


    }

}
