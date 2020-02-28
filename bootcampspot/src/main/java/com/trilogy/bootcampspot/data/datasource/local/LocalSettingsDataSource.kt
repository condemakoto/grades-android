package com.trilogy.bootcampspot.data.datasource.local

import android.content.Context
import com.trilogy.bootcampspot.data.datasource.SettingsDataSource

class LocalSettingsDataSource(context: Context) : SettingsDataSource {

    val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return sharedPreferences.getString("authToken", null)
    }

    override fun setToken(token: String?) {
        val edit = sharedPreferences.edit()
        edit.putString("authToken", token)
        edit.commit()
    }

}