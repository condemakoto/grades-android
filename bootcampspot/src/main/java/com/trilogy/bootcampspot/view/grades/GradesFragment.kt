package com.trilogy.bootcampspot.view.grades

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.view.base.BaseFragment
import com.trilogy.bootcampspot.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_grades.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GradesFragment : BaseFragment<GradesViewState, GradesViewModel>(), GradesAdapter.AssignmentClickListener {

    override fun initViewModel(): GradesViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_grades
    }

    override fun onViewStateUpdated(viewState: GradesViewState) {
        loadingView.visibility = if (viewState.loading) View.VISIBLE else View.GONE
        viewState.grades?.let{
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = GradesAdapter(it, this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewInitialized()
    }

    override fun onAssignmentClicked(assignmentId: Int) {
        (activity as DashboardActivity).onAssignmentClicked(assignmentId)
    }
}