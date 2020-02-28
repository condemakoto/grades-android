package com.trilogy.bootcampspot.view.util

import com.conde.kun.core.view.BaseViewModel

abstract class BaseAppViewModel<V> : BaseViewModel<V>() {

    abstract fun onViewInitialized()

}