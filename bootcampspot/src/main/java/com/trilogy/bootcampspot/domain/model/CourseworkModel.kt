package com.trilogy.bootcampspot.domain.model

import com.trilogy.bootcampspot.data.net.response.SubmissionComment

data class CourseworkModel(
    val assignmentTitle: String?,
    val assignmentHeader: String?,
    val assignmentContent: String?,
    val grade: String?,
    val submissionDate: String?,
    val submittedUrl: List<String>,
    val comments: List<SubmissionComment>
)