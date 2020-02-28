package com.trilogy.bootcampspot.view.grades

import com.conde.kun.core.error.BaseException
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.data.net.response.Grade
import com.trilogy.bootcampspot.domain.model.GeneralInfo
import com.trilogy.bootcampspot.domain.usecase.GetGeneralInfoUseCase
import com.trilogy.bootcampspot.domain.usecase.GetGradesUseCase
import io.reactivex.observers.DisposableSingleObserver

class GradesViewModel(
    private val getGeneralInfoUseCase: GetGeneralInfoUseCase,
    private val getGradesUseCase: GetGradesUseCase
) :
    BaseViewModel<GradesViewState>() {

    override fun getInitialViewState(): GradesViewState {
        return GradesViewState()
    }

    fun onViewInitialized() {
        getGeneralInfoUseCase.execute(object : DisposableSingleObserver<GeneralInfo>() {
            override fun onSuccess(t: GeneralInfo) {
                getGrades(t.enrollmentInfo[0].enrollmentId)
            }

            override fun onError(e: Throwable) {
                val vs = viewState.value!!
                vs.error = e as BaseException
                vs.loading = false
                viewState.value = vs
            }
        }, null)
    }

    private fun getGrades(enrollmentId: Int) {
        getGradesUseCase.execute(object : DisposableSingleObserver<List<Grade>>() {
            override fun onSuccess(t: List<Grade>) {
                val vs = viewState.value!!
                vs.loading = false
                vs.grades = t
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

class GradesViewState {
    var loading = true
    var error: BaseException? = null
    var grades: List<Grade>? = null
}