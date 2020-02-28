package com.trilogy.bootcampspot.view.dashboard.userinfo

import com.conde.kun.core.view.BaseViewModel

class UserInfoViewModel : BaseViewModel<UserInfoViewState>() {

    override fun getInitialViewState(): UserInfoViewState {
        return UserInfoViewState()
    }
}

class UserInfoViewState {

}