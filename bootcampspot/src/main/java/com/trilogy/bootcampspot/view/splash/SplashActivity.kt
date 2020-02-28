package com.trilogy.bootcampspot.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conde.kun.core.view.BaseActivity
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.view.dashboard.DashboardActivity
import com.trilogy.bootcampspot.view.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<SplashViewState, SplashViewModel>() {

    override fun initViewModel(): SplashViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_splash
    }

    override fun onViewStateUpdated(viewState: SplashViewState) {
        if (viewState.showLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        if (viewState.showDashboard) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewInitialized()
    }
}
