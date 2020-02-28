package com.trilogy.bootcampspot.data.datasource.remote

import com.trilogy.bootcampspot.data.datasource.BcsDataSource
import com.trilogy.bootcampspot.data.net.BcsApi
import com.trilogy.bootcampspot.data.net.request.CourseworkRequest
import com.trilogy.bootcampspot.data.net.request.DashboardRequest
import com.trilogy.bootcampspot.data.net.request.LoginRequest
import com.trilogy.bootcampspot.data.net.request.RequestResetPasswordRequest
import com.trilogy.bootcampspot.data.net.response.*
import io.reactivex.Single

class RemoteBcsDataSource(private val bcsApi: BcsApi) : BcsDataSource {

    override fun login(username: String, password: String): Single<LoginResponse> {
        val request = LoginRequest(username, password)
        return bcsApi.login(request)
    }

    override fun requestPasswordReset(username: String): Single<BaseResponse> {
        val request = RequestResetPasswordRequest(username, "http://com.trilogy.bootcampspot")
        return bcsApi.requestResetPassword(request)
    }

    override fun getUserInfo(): Single<MeResponse> {
        return bcsApi.getUserInfo()
    }

    override fun getDashboard(enrollmentId: Int): Single<DashboardResponse> {
        return bcsApi.getDashboard(DashboardRequest(enrollmentId))
    }

    override fun getGrades(enrollmentId: Int): Single<List<GradesResponse>> {
        return bcsApi.getGrades(DashboardRequest(enrollmentId))
    }

    override fun getCourseworkDetail(assignmentId: Int): Single<CourseworkResponse> {
        return bcsApi.getCourseworkDetail(CourseworkRequest(assignmentId))
    }
}