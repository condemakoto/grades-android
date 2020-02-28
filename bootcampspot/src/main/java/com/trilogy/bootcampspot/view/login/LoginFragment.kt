package com.trilogy.bootcampspot.view.onboarding.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.conde.kun.core.error.BaseException
import com.conde.kun.core.view.IActivity
import com.trilogy.bootcampspot.R
import com.trilogy.bootcampspot.data.exception.InvalidCredentialsException
import com.trilogy.bootcampspot.view.base.BaseFragment
import com.trilogy.bootcampspot.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment : BaseFragment<LoginViewState, LoginViewModel>() {

    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_login
    }

    override fun onStart() {
        super.onStart()
        if (activity is IActivity) {
            (activity as IActivity).setTitle(getString(R.string.login))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton.setOnClickListener {
            viewModel.login(
                emailTIL.editText?.text.toString(),
                passwordTIL.editText?.text.toString()
            )
        }
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
        passwordTIL.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submitButton.performClick()
                true
            } else {
                false
            }
        }
        emailTIL.editText?.addTextChangedListener(errorDismisser)
        passwordTIL.editText?.addTextChangedListener(errorDismisser)
        forgotPasswordButton.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)}
    }

    override fun onViewStateUpdated(viewState: LoginViewState) {

        processError(viewState.error)

        emailTIL.error =
            if (viewState.useraccountError) getString(R.string.username_error) else null
        passwordTIL.error =
            if (viewState.passwordError) getString(R.string.password_error) else null
        submitButton.isEnabled = !viewState.loading

        emailTIL.isEnabled = !viewState.loading
        passwordTIL.isEnabled = !viewState.loading
        loadingView.visibility = if (viewState.loading) View.VISIBLE else View.INVISIBLE

        if (viewState.loginSuccess) {
            startActivity(Intent(context, DashboardActivity::class.java))
            activity?.finish()
        }
    }

    override fun processError(exception: BaseException?) {
        if (exception is InvalidCredentialsException) {
            showSnackbarError(getString(R.string.error_invalid_credentials))
        } else {
            super.processError(exception)
        }
    }
}