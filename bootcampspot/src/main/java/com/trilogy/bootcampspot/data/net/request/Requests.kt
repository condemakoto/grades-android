package com.trilogy.bootcampspot.data.net.request

data class LoginRequest(val email: String, val password: String)

data class RequestResetPasswordRequest(val username: String, val resetUrl: String)

data class DashboardRequest(val enrollmentId: Int?)

data class CourseworkRequest(val assignmentId: Int?)