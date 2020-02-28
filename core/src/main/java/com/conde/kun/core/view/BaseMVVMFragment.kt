package com.conde.kun.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseMVVMFragment<VS, VM : BaseViewModel<VS>> : Fragment() {

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
        viewModel = initViewModel()
        viewModel.viewState.observe(this, viewStateObserver)
        return view
    }

    private val viewStateObserver = Observer<VS> { viewState ->
        onViewStateUpdated(viewState)
    }

    abstract fun initViewModel(): VM
    abstract fun getLayoutResource(): Int
    abstract fun onViewStateUpdated(viewState: VS)

}