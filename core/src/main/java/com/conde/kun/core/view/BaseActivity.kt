package com.conde.kun.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<VS, VM : BaseViewModel<VS>> : AppCompatActivity() {
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        viewModel.viewState.observe(this, viewStateObserver)
        setContentView(getLayoutResource())
    }

    private val viewStateObserver = Observer<VS> { viewState ->
        onViewStateUpdated(viewState)
    }

    abstract fun initViewModel(): VM
    abstract fun getLayoutResource(): Int
    abstract fun onViewStateUpdated(viewState: VS)


}