package com.trilogy.bootcampspot.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnrollmentInfo(
    @PrimaryKey
    val enrollmentId: Int,
    val courserName: String,
    val courseLocation: String?,
    val courseRole: String,
    val courseStartDate: String,
    val courseEndDate: String,
    val programName: String,
    val programType: String,
    val universityName: String,
    val universityLogo: String?
)