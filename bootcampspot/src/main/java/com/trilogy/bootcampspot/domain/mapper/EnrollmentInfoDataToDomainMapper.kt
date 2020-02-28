package com.trilogy.bootcampspot.domain.mapper

import com.conde.kun.core.domain.BaseMapper
import com.trilogy.bootcampspot.data.EnrollmentInfo
import com.trilogy.bootcampspot.domain.model.CourseInfo
import com.trilogy.bootcampspot.domain.model.ProgramInfo

class EnrollmentInfoDataToDomainMapper :
    BaseMapper<EnrollmentInfo, com.trilogy.bootcampspot.domain.model.EnrollmentInfo>() {

    override fun map(input: EnrollmentInfo): com.trilogy.bootcampspot.domain.model.EnrollmentInfo {

        val courseInfo = CourseInfo(
            input.courserName,
            input.courseLocation,
            input.courseRole,
            input.courseStartDate,
            input.courseEndDate
        )

        val programInfo = ProgramInfo(
            input.programName,
            input.programType,
            input.universityName,
            input.universityLogo
        )

        return com.trilogy.bootcampspot.domain.model.EnrollmentInfo(
            input.enrollmentId,
            courseInfo,
            programInfo
        )
    }
}