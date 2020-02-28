package com.trilogy.bootcampspot.view.splash

import androidx.lifecycle.viewModelScope
import com.conde.kun.core.domain.BaseResourceObserver
import com.conde.kun.core.domain.Status
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.domain.usecase.CheckUserSignedInUseCase
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val checkUserSignedInUseCase: CheckUserSignedInUseCase) :
    BaseViewModel<SplashViewState>() {

    override fun getInitialViewState(): SplashViewState {
        return SplashViewState()
    }

    fun onViewInitialized() {

        checkUserSignedInUseCase.execute(
            object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    viewModelScope.launch {
                        delay(1000)
                        val vs = viewState.value!!
                        vs.showDashboard = t
                        vs.showLogin = !vs.showDashboard
                        viewState.value = vs
                    }
                }

                override fun onError(e: Throwable) {
                    //do nothing
                }
            },
            null
        )

    }
}

class SplashViewState {
    var showLogin = false
    var showDashboard = false
}