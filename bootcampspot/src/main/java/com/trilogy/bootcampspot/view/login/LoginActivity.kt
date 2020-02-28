package com.trilogy.bootcampspot.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conde.kun.core.view.IActivity
import com.trilogy.bootcampspot.R

class LoginActivity : AppCompatActivity(), IActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

}