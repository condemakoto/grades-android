package com.trilogy.bootcampspot.view.onboarding.login

import com.conde.kun.core.error.BaseException

class LoginViewState {

    var loading = false
    var error: BaseException? = null
    var useraccountError = false
    var passwordError = false
    var loginSuccess = false

}