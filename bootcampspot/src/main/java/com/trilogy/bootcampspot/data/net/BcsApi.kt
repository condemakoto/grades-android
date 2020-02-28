package com.trilogy.bootcampspot.data.net

import com.trilogy.bootcampspot.data.net.request.CourseworkRequest
import com.trilogy.bootcampspot.data.net.request.DashboardRequest
import com.trilogy.bootcampspot.data.net.request.LoginRequest
import com.trilogy.bootcampspot.data.net.request.RequestResetPasswordRequest
import com.trilogy.bootcampspot.data.net.response.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BcsApi {

    @POST("/broker/login")
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @POST("/broker/requestPasswordReset")
    fun requestResetPassword(@Body request: RequestResetPasswordRequest): Single<BaseResponse>

    @GET("/broker/me")
    fun getUserInfo(): Single<MeResponse>

    @POST("/broker/dashboard")
    fun getDashboard(@Body request: DashboardRequest): Single<DashboardResponse>

    @POST("/broker/grades")
    fun getGrades(@Body request: DashboardRequest): Single<List<GradesResponse>>

    @POST("/broker/courseworkDetail")
    fun getCourseworkDetail(@Body request: CourseworkRequest): Single<CourseworkResponse>
}