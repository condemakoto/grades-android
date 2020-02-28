package com.trilogy.bootcampspot.view.bridge

import android.content.Context
import android.content.Intent
import com.trilogy.bootcampspot.view.login.LoginActivity

class LoginBridge(private val context: Context) {

    fun showLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}