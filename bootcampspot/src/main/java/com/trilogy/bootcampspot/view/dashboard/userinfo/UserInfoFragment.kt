package com.trilogy.bootcampspot.view.dashboard.userinfo

import com.trilogy.bootcampspot.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserInfoFragment : BaseFragment<UserInfoViewState, UserInfoViewModel>() {

    override fun initViewModel(): UserInfoViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewStateUpdated(viewState: UserInfoViewState) {

    }
}