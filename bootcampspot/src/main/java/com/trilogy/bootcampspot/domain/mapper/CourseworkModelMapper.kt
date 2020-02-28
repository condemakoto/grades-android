package com.trilogy.bootcampspot.domain.mapper

import com.conde.kun.core.domain.BaseMapper
import com.trilogy.bootcampspot.data.net.response.CourseworkResponse
import com.trilogy.bootcampspot.domain.model.CourseworkModel

class CourseworkModelMapper : BaseMapper<CourseworkResponse, CourseworkModel>() {

    override fun map(input: CourseworkResponse): CourseworkModel {
        return CourseworkModel(
            input.assignment!!.title,
            input.assignment.assignmentHeader!!.header,
            input.assignment.assignmentContent!!.content,
            input.assignment.submission?.submissionGrade?.grade,
            input.assignment.submission!!.date,
            input.assignment.submission.submissionUrlList.map {
                it.url!!
            },
            input.assignment.submission.submissionCommentList
        )

    }

}