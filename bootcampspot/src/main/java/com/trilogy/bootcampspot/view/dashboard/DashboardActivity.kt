package com.trilogy.bootcampspot.view.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.conde.kun.core.view.BaseActivity
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.view.login.LoginActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DashboardActivity : BaseActivity<DashboardViewState, DashboardViewModel>() {

    override fun initViewModel(): DashboardViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_dashboard
    }

    override fun onViewStateUpdated(viewState: DashboardViewState) {
        if (viewState.logout) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        viewModel.onViewInitialized()

        val fr = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, fr.navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.notifications -> {
                AlertDialog.Builder(this)
                    .setMessage(R.string.empty_notifications)
                    .setPositiveButton(android.R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)


    }

    fun logout() {
        AlertDialog.Builder(this)
            .setMessage(R.string.logout_confirmation)
            .setPositiveButton(android.R.string.yes) { dialog, _ ->
                dialog.dismiss()
                viewModel.logout()
            }
            .setNegativeButton(android.R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

    fun onAssignmentClicked(assignmentId: Int) {
        val args = Bundle()
        args.putInt("assignmentId", assignmentId)
        fragmentContainer.findNavController().navigate(R.id.openSubmissionAction, args)
    }

    fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setHomeButtonEnabled(show)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.fragmentContainer)
        if (navController.currentDestination?.id == R.id.submissionFragment) {
            showBackButton(false)
            bottomNavigationView.visibility = View.VISIBLE
            navController.popBackStack()
        } else {
            moveTaskToBack(true)
        }
    }
}