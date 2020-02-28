package com.trilogy.bootcampspot.view.dashboard.generalinfo

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.view.base.BaseFragment
import com.trilogy.bootcampspot.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_general_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.Exception
import com.google.android.gms.security.ProviderInstaller
import android.os.Build.VERSION.SDK_INT
import com.trilogy.bootcampspot.view.util.getGithubUserImage


class GeneralInfoFragment : BaseFragment<GeneralInfoViewState, GeneralInfoViewModel>() {

    override fun initViewModel(): GeneralInfoViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_general_info
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewInitialized()
        logoutButton.setOnClickListener {
            (activity as DashboardActivity).logout()
        }

        if (SDK_INT === 19) {
            try {
                ProviderInstaller.installIfNeeded(context)
            } catch (ignored: Exception) {
                //do onthing
            }
        }
    }

    override fun onViewStateUpdated(viewState: GeneralInfoViewState) {
        viewState.useraccount?.let {
            nameTV.text = String.format("%s %s", it.firstName, it.lastName)
            programNameTV.text = it.email

            if (it.githubUserName != null) {
                val imageUrl = getGithubUserImage(it.githubUserName)
                Picasso.get().load(imageUrl).into(userImage)
            }
        }

        viewState.enrollmentInfo?.let {
            roleTV.text = it.courseInfo.role
            programNameTV.text = it.programInfo.programType
            universityNameTV.text = it.programInfo.universityName
            Picasso.get().load(it.programInfo.universityLogo).into(universityImage)
        }

        viewState.studentResume?.let {
            averageTV.text = it.academicAverageGrade
            overdueTV.text = it.overdueAssignmentCount?.toString()
            val average: Float = if (it.academicAverageValue == null) 0f else it.academicAverageValue
            academicAverageTV.text = String.format("%.2f", average)
            academicAverageChart.perc = average
            academicAverageChart.invalidate()
        }


        locationButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.location)
                .setMessage(viewState.enrollmentInfo?.courseInfo?.location)
                .setPositiveButton(android.R.string.yes) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }
}