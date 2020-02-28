package com.trilogy.bootcampspot.data.net.response

data class AuthenticationInfo(
    val userId: Int?,
    val firstLogin: Boolean?,
    val active: Boolean?,
    val authToken: String?
)

data class LoginResponse(
    val success: Boolean?,
    val errorCode: String?,
    val resetToken: Boolean?,
    val authenticationInfo: AuthenticationInfo?
)

data class BaseResponse(val success: Boolean?, val errorCode: String?)

data class MeResponse(
    val userAccount: UserAccount?,
    val enrollments: List<Enrollment>?
)

data class UserAccount(
    val id: Int?,
    val userName: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val mailingLogoUrl: String?,
    val githubUserName: String?
)

data class Enrollment(
    val id: Int?,
    val courseId: Int?,
    val courseRoleId: Int?,
    val active: Boolean?,
    val courseRole: CourseRole?,
    val course: Course?,
    val remoteAttendance: Int?,
    val pendingRemoteAttendanceRequestCount: Int?,
    val showCareerServicesEvents: Boolean?
)

data class CourseRole(
    val id: Int?,
    val name: String?,
    val courseRoleCode: String?
)

data class Course(
    val id: Int?,
    val cohortId: Int?,
    val name: String?,
    val startDate: String?,
    val endDate: String?,
    val location: String?,
    val cohort: Cohort?
)

data class Cohort(
    val name: String?,
    val division: String?,
    val program: Program?
)

data class Program(
    val id: Int?,
    val name: String?,
    val programType: ProgramType?,
    val university: University?
)

data class ProgramType(
    val name: String?
)

data class University(
    val id: Int?,
    val name: String?,
    val logoUrl: String?
)

data class DashboardResponse (
    val studentResume: StudentResume
)

data class StudentResume (
    val overdueAssignmentCount: Int?,
    val maxAllowedAbsences: Int?,
    val remainingAbsenceCount: Int?,
    val careerCompletionPercentage: Int?,
    val academicAverageValue: Int?,
    val academicAverageGrade: String?
)
