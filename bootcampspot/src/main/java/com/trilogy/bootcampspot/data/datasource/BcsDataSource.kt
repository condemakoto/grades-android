package com.trilogy.bootcampspot.data.datasource

import com.trilogy.bootcampspot.data.net.response.*
import io.reactivex.Single

interface BcsDataSource {

    fun login(username: String, password: String): Single<LoginResponse>
    fun requestPasswordReset(username: String): Single<BaseResponse>
    fun getUserInfo(): Single<MeResponse>
    fun getDashboard(enrollmentId: Int): Single<DashboardResponse>
    fun getGrades(enrollmentId: Int): Single<List<Grade>>
    fun getCourseworkDetail(assignmentId: Int): Single<CourseworkResponse>
}