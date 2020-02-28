package com.trilogy.bootcampspot.data.datasource

import com.trilogy.bootcampspot.data.net.response.UserAccount
import io.reactivex.Single

interface UserDataSource {
    fun storeUserAccount(userAccount: UserAccount)
    fun getUserAccount(): Single<UserAccount>
    fun storeEnrollmentId(enrollmentId: Int)
    fun getEnrollmentId() : Single<Int>
}