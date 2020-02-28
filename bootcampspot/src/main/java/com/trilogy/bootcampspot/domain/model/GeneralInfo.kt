package com.trilogy.bootcampspot.domain.model

import com.trilogy.bootcampspot.data.net.response.UserAccount

data class GeneralInfo(
    val userAccount: UserAccount,
    val enrollmentInfo: List<EnrollmentInfo>
)