package com.trilogy.bootcampspot.view.forgotpassword

import com.conde.kun.core.error.BaseException
import com.conde.kun.core.util.isEmailValid
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.domain.usecase.RequestPasswordResetUseCase
import io.reactivex.observers.DisposableSingleObserver

class ForgotPasswordViewModel(private val requestPasswordResetUseCase: RequestPasswordResetUseCase) :
    BaseViewModel<ForgotPasswordViewState>() {

    override fun getInitialViewState(): ForgotPasswordViewState {
        return ForgotPasswordViewState()
    }

    fun onForgotPasswordClicked(email: String) {
        val value = viewState.value ?: getInitialViewState()
        value.error = null
        value.useraccountError = !isEmailValid(email)
        if (!value.useraccountError) {
            value.loading = true
            viewState.value = value

            requestPasswordResetUseCase.execute(object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(t: Boolean) {
                    val value = viewState.value ?: getInitialViewState()
                    if (t) {
                        value.success = true
                    } else {
                        value.error = BaseException()
                    }
                    viewState.value = value
                }

                override fun onError(e: Throwable) {
                    val value = viewState.value ?: getInitialViewState()
                    value.loading = false
                    value.error = e as BaseException
                    viewState.value = value
                }
            }, email)
        }
    }

    fun onInputChanged() {
        val value = viewState.value ?: getInitialViewState()
        value.useraccountError = false
        viewState.value = value
    }
}


class ForgotPasswordViewState {
    var loading = false
    var useraccountError = false
    var error: BaseException? = null
    var success = false
}