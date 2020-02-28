package com.trilogy.bootcampspot.view.base

import com.conde.kun.core.domain.BaseError
import com.conde.kun.core.error.BaseException
import com.conde.kun.core.error.BusinessErrorException
import com.conde.kun.core.error.NoInternetException
import com.conde.kun.core.error.ServerErrorException
import com.conde.kun.core.view.BaseMVVMFragment
import com.conde.kun.core.view.BaseViewModel
import com.trilogy.bootcampspot.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VS, VM : BaseViewModel<VS>> : BaseMVVMFragment<VS, VM>() {

    var snackbar: Snackbar? = null

    protected fun showSnackbarError(errorString: String) {
        view?.let {
            snackbar = Snackbar.make(it, errorString, Snackbar.LENGTH_INDEFINITE)
            snackbar?.show()
        }
    }

    protected fun hideSnackbarError() {
        snackbar?.dismiss()
    }

    protected open fun processError(exception: BaseException?) {
        when (exception) {
            is NoInternetException -> showSnackbarError(getString(R.string.error_no_internet))
            is ServerErrorException -> showSnackbarError(getString(R.string.error_server))
            is BusinessErrorException -> {
                processBusinessError(exception.error)
            }
            null -> hideSnackbarError()
            else -> showSnackbarError(getString(R.string.error_server))
        }
    }

    protected open fun processBusinessError(error: BaseError) {
        showSnackbarError(getString(R.string.error_server))
    }

}