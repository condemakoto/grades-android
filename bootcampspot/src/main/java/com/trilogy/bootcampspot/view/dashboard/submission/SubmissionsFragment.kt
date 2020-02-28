package com.trilogy.bootcampspot.view.dashboard.submission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.squareup.picasso.Picasso
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.data.net.response.SubmissionComment
import com.trilogy.bootcampspot.view.base.BaseFragment
import com.trilogy.bootcampspot.view.dashboard.DashboardActivity
import com.trilogy.bootcampspot.view.util.getGithubUserImage
import kotlinx.android.synthetic.main.fragment_submissions.*
import kotlinx.android.synthetic.main.item_comment.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_comment.view.userImage
import kotlinx.android.synthetic.main.item_row_button.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SubmissionsFragment : BaseFragment<SubmissionsViewState, SubmissionsViewModel>() {

    override fun initViewModel(): SubmissionsViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_submissions
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val assignmentId = this.arguments!!.getInt("assignmentId")
        viewModel.onViewInitialized(assignmentId)

        activity?.let{(it as DashboardActivity).showBackButton(true)}
    }

    override fun onViewStateUpdated(viewState: SubmissionsViewState) {
        loadingView.visibility = if (viewState.loading) View.VISIBLE else View.INVISIBLE

        viewState.courseworkDetail?.let {
            submissionNameTv.text = it.assignmentTitle
            submissionHeaderTv.text = it.assignmentHeader
            submissionDescriptionTv.text = it.assignmentContent

            val inflater = LayoutInflater.from(context)

            if (it.submittedUrl == null || it.submittedUrl.isEmpty()) {
                submittedLinksEmptyView.visibility = View.VISIBLE
            } else {
                submittedLinksEmptyView.visibility = View.GONE
                it.submittedUrl.forEach { url ->
                    val view =
                        inflater.inflate(R.layout.item_row_button, submittedLinksContainer, false)
                    view.textView.text = url
                    view.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                    submittedLinksContainer.addView(view)
                }
            }

            if (it.comments == null || it.comments.isEmpty()) {
                commentsEmptyView.visibility = View.VISIBLE
            } else {
                commentsEmptyView.visibility = View.GONE
                it.comments.forEach {
                    comment: SubmissionComment ->
                    val view = inflater.inflate(R.layout.item_comment, commentsContainer, false)
                    view.commentTV.text = comment.comment
                    val userImageUrl = getGithubUserImage(comment.author?.githubUserName)
                    Picasso.get().load(userImageUrl).into(view.userImage)
                    view.commentDateTV.text = comment.date
                    commentsContainer.addView(view)
                }
            }

        }

    }

}