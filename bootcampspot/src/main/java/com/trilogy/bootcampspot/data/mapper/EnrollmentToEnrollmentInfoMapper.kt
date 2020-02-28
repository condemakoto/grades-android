package com.trilogy.bootcampspot.data.mapper

import com.conde.kun.core.domain.BaseMapper
import com.trilogy.bootcampspot.data.EnrollmentInfo
import com.trilogy.bootcampspot.data.net.response.Enrollment

class EnrollmentToEnrollmentInfoMapper : BaseMapper<Enrollment, EnrollmentInfo>() {

    override fun map(input: Enrollment): EnrollmentInfo {

        val course = input.course!!
        val cohort = course.cohort!!
        val program = cohort.program!!
        val university = program.university!!

        return EnrollmentInfo(
            input.id!!,
            course.name!!,
            course.location,
            input.courseRole?.name!!,
            course.startDate!!,
            course.endDate!!,
            program.name!!,
            program.programType?.name!!,
            university.name!!,
            university.logoUrl
        )

    }
}