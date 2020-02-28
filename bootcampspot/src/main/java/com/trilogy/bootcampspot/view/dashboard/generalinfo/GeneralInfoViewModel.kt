package com.trilogy.bootcampspot.view.dashboard.generalinfo

import com.conde.kun.core.error.BaseException
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.data.net.response.DashboardResponse
import com.trilogy.bootcampspot.data.net.response.StudentResume
import com.trilogy.bootcampspot.data.net.response.UserAccount
import com.trilogy.bootcampspot.domain.model.EnrollmentInfo
import com.trilogy.bootcampspot.domain.model.GeneralInfo
import com.trilogy.bootcampspot.domain.usecase.GetDashboardUseCase
import com.trilogy.bootcampspot.domain.usecase.GetGeneralInfoUseCase
import com.trilogy.bootcampspot.view.util.BaseViewState
import io.reactivex.observers.DisposableSingleObserver

class GeneralInfoViewModel(
    private val getGeneralInfoUseCase: GetGeneralInfoUseCase,
    private val getDashboardUseCase: GetDashboardUseCase
) :
    BaseViewModel<GeneralInfoViewState>() {

    override fun getInitialViewState(): GeneralInfoViewState {
        return GeneralInfoViewState()
    }

    fun onViewInitialized() {

        getGeneralInfoUseCase.execute(object : DisposableSingleObserver<GeneralInfo>() {
            override fun onSuccess(t: GeneralInfo) {
                val vs = viewState.value!!
                vs.useraccount = t.userAccount
                vs.enrollmentInfo = t.enrollmentInfo[0]
                viewState.value = vs
                getDashboard(t.enrollmentInfo[0].enrollmentId)
            }

            override fun onError(e: Throwable) {
                val vs = viewState.value!!
                vs.error = e as BaseException
                viewState.value = vs
            }
        }, null)

    }

    private fun getDashboard(enrollmentId: Int) {
        getDashboardUseCase.execute(object : DisposableSingleObserver<DashboardResponse>() {
            override fun onSuccess(t: DashboardResponse) {
                val vs = viewState.value!!
                vs.studentResume = t.studentResume
                vs.loading = false
                viewState.value = vs
            }

            override fun onError(e: Throwable) {
                val vs = viewState.value!!
                vs.error = e as BaseException
                vs.loading = false
                viewState.value = vs
            }
        }, enrollmentId)
    }
}

class GeneralInfoViewState : BaseViewState() {
    init {
        loading = true
    }

    var useraccount: UserAccount? = null
    var enrollmentInfo: EnrollmentInfo? = null
    var studentResume: StudentResume? = null
}