package com.trilogy.bootcampspot.domain.model

data class EnrollmentInfo(
    val enrollmentId: Int,
    val courseInfo: CourseInfo,
    val programInfo: ProgramInfo
)

data class CourseInfo(
    val name: String,
    val location: String?,
    val role: String,
    val startDate: String,
    val endDate: String
)

data class ProgramInfo(
    val programName: String,
    val programType: String,
    val universityName: String,
    val universityLogo: String?
)