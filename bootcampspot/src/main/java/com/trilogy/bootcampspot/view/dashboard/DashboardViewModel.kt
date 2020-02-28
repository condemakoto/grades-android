package com.trilogy.bootcampspot.view.dashboard

import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.domain.usecase.GetEnrollmentIdUseCase
import com.trilogy.bootcampspot.domain.usecase.LogoutUseCase
import io.reactivex.observers.DisposableSingleObserver

class DashboardViewModel(
    private val getEnrollmentIdUseCase: GetEnrollmentIdUseCase,
    private val logoutUseCase: LogoutUseCase
) :
    BaseViewModel<DashboardViewState>() {

    override fun getInitialViewState(): DashboardViewState {
        return DashboardViewState()
    }

    fun onViewInitialized() {

        val vs = viewState.value!!
        vs.loading = true
        viewState.value = vs

        getEnrollmentIdUseCase.execute(object : DisposableSingleObserver<Int>() {
            override fun onSuccess(t: Int) {
                val vs = viewState.value!!
                vs.enrollmentId = t
                viewState.value = vs

            }

            override fun onError(e: Throwable) {
                val vs = viewState.value!!
                vs.error = true
                viewState.value = vs
            }
        }, null)
    }

    fun logout() {

        logoutUseCase.execute(object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                val vs = viewState.value!!
                vs.logout = true
                viewState.value = vs
            }

            override fun onError(e: Throwable) {
                //do nothing
            }
        }, null)
    }
}

class DashboardViewState {
    var loading: Boolean = false
    var enrollmentId: Int? = null
    var error: Boolean = false
    var logout = false
}