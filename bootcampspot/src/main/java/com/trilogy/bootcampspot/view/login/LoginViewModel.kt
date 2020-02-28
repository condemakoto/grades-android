package com.trilogy.bootcampspot.view.onboarding.login

import com.conde.kun.core.error.BaseException
import com.conde.kun.core.util.isEmailValid
import com.conde.kun.core.util.isPasswordValid
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.data.net.response.MeResponse
import com.trilogy.bootcampspot.domain.usecase.GetUserInfoUseCase
import com.trilogy.bootcampspot.domain.usecase.LoginUseCase
import io.reactivex.observers.DisposableSingleObserver

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel<LoginViewState>() {


    override fun getInitialViewState(): LoginViewState {
        return LoginViewState()
    }

    fun login(userAccount: String, password: String) {

        val value = viewState.value ?: getInitialViewState()
        value.error = null
        value.useraccountError = !isEmailValid(userAccount)
        value.passwordError = !isPasswordValid(password)
        value.loading = !value.useraccountError && !value.passwordError
        viewState.value = value

        if (value.loading) {

            loginUseCase.execute(object : DisposableSingleObserver<Boolean>() {

                override fun onSuccess(t: Boolean) {
                    getUserInfo()
                }

                override fun onError(e: Throwable) {
                    val value = viewState.value ?: getInitialViewState()
                    value.loading = false
                    value.error = e as BaseException
                    viewState.value = value
                }
            }, LoginUseCase.LoginParams(userAccount, password))
        }

    }

    private fun getUserInfo() {
        getUserInfoUseCase.execute(object : DisposableSingleObserver<MeResponse>() {
            override fun onSuccess(t: MeResponse) {
                val value = viewState.value!!
                value.loginSuccess = true
                viewState.value = value
            }

            override fun onError(e: Throwable) {
                val value = viewState.value!!
                value.loading = false
                value.error = e as BaseException
                viewState.value = value
            }
        }, null)

    }

    fun onInputChanged() {
        val value = viewState.value ?: getInitialViewState()
        value.passwordError = false
        value.useraccountError = false
        viewState.value = value
    }
}