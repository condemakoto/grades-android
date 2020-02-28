package com.trilogy.bootcampspot.data.datasource.local

import android.content.Context
import com.trilogy.bootcampspot.data.datasource.UserDataSource
import com.trilogy.bootcampspot.data.net.response.UserAccount
import io.reactivex.Single

class LocalUserDataSource(context: Context) : UserDataSource {

    val sp = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    override fun storeUserAccount(userAccount: UserAccount) {
        val edit = sp.edit()
        edit.putInt("userId", userAccount.id!!)
        edit.putString("username", userAccount.userName)
        edit.putString("firstName", userAccount.firstName)
        edit.putString("lastName", userAccount.lastName)
        edit.putString("email", userAccount.email)
        edit.putString("githubUserName", userAccount.githubUserName)
        edit.commit()
    }

    override fun getUserAccount(): Single<UserAccount> {
        return Single.just(
            UserAccount(
                sp.getInt("id", 0),
                sp.getString("username", null),
                sp.getString("firstName", null),
                sp.getString("lastName", null),
                sp.getString("email", null),
                null,
                sp.getString("githubUserName", null)
            )
        )
    }

    override fun storeEnrollmentId(enrollmentId: Int) {
        val edit = sp.edit()
        edit.putInt("enrollmentId", enrollmentId)
        edit.commit()
    }

    override fun getEnrollmentId(): Single<Int> {
        return Single.just(sp.getInt("enrollmentId", -1))
    }
}