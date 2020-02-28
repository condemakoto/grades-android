package com.trilogy.bootcampspot.view.dashboard.submission

import com.conde.kun.core.error.BaseException
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.domain.model.CourseworkModel
import com.trilogy.bootcampspot.domain.usecase.GetCourseworkUseCase
import io.reactivex.observers.DisposableSingleObserver

class SubmissionsViewModel(
    private val getCourseworkUseCase: GetCourseworkUseCase
) : BaseViewModel<SubmissionsViewState>() {

    override fun getInitialViewState(): SubmissionsViewState {
        return SubmissionsViewState()
    }

    fun onViewInitialized(assignmentId: Int) {
        getCourseworkUseCase.execute(object : DisposableSingleObserver<CourseworkModel>() {
            override fun onSuccess(t: CourseworkModel) {
                val vs = viewState.value!!
                vs.loading = false
                vs.courseworkDetail = t
                viewState.value = vs
            }

            override fun onError(e: Throwable) {
                val vs = viewState.value!!
                vs.loading = false
                vs.error = e as BaseException
                viewState.value = vs
            }
        }, assignmentId)

    }
}

class SubmissionsViewState {
    var loading = true
    var error: BaseException? = null
    var courseworkDetail: CourseworkModel? = null
}