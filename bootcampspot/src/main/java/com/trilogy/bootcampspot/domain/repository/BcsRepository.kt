package com.trilogy.bootcampspot.domain.repository

import com.trilogy.bootcampspot.data.net.response.DashboardResponse
import com.trilogy.bootcampspot.data.net.response.Grade
import com.trilogy.bootcampspot.data.net.response.MeResponse
import com.trilogy.bootcampspot.data.net.response.UserAccount
import com.trilogy.bootcampspot.domain.model.CourseworkModel
import com.trilogy.bootcampspot.domain.model.EnrollmentInfo
import io.reactivex.Single

interface BcsRepository {

    fun login(username: String, password: String): Single<Boolean>
    fun requestPasswordReset(username: String): Single<Boolean>
    fun getUserInfo(): Single<MeResponse>
    fun getDashboard(enrollmentId: Int): Single<DashboardResponse>
    fun getUserAccount(): Single<UserAccount>
    fun getGrades(enrollmentId: Int): Single<List<Grade>>
    fun getEnrollmentId(): Single<Int>
    fun listEnrollmentInfo(): Single<List<EnrollmentInfo>>
    fun findCourseworkDetail(assignmentId: Int): Single<CourseworkModel>
}
