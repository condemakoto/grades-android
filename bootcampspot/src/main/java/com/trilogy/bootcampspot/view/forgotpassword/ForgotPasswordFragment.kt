package com.trilogy.bootcampspot.view.forgotpassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conde.kun.core.view.IActivity
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ForgotPasswordFragment :
    BaseFragment<ForgotPasswordViewState, ForgotPasswordViewModel>() {

    override fun onStart() {
        super.onStart()
        if (activity is IActivity) {
            (activity as IActivity).setTitle(getString(R.string.forgot_password))
        }
    }

    override fun initViewModel(): ForgotPasswordViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_forgot_password
    }

    override fun onViewStateUpdated(viewState: ForgotPasswordViewState) {
        processError(viewState.error)
        emailTIL.error =
            if (viewState.useraccountError) getString(R.string.username_error) else null
        submitButton.isEnabled = !viewState.loading
        emailTIL.isEnabled = !viewState.loading
        successView.visibility = if (viewState.success) View.VISIBLE else View.GONE
        contentView.visibility = if (viewState.success) View.GONE else View.VISIBLE
        loadingView.visibility = if (viewState.loading) View.VISIBLE else View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener { findNavController().popBackStack() }
        goBackButton.setOnClickListener { findNavController().popBackStack() }
        submitButton.setOnClickListener { viewModel.onForgotPasswordClicked(emailTIL.editText?.text.toString()) }
        val errorDismisser = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onInputChanged()
            }
        }
        emailTIL.editText?.addTextChangedListener(errorDismisser)
    }


}