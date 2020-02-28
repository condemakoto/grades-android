package com.trilogy.bootcampspot.data.datasource

interface SettingsDataSource {
    fun getToken(): String?
    fun setToken(token: String?)
}