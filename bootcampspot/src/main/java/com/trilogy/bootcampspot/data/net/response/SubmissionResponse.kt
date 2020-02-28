package com.trilogy.bootcampspot.data.net.response

data class GradesResponse(
    val assignment: Assignment,
    val context: AssignmentContext,
    val submission: Submission,
    val submissionGrade: SubmissionGrade,
    val commentsCount: Int?,
    val prework: Boolean?,
    val assignmentContent: AssignmentContent?
)

data class Assignment(
    val id: Int?,
    val courseId: Int?,
    val assignmentData: String?,
    val dueDate: String?,
    val bufferDays: Int?,
    val effectiveDueDate: String?,
    val title: String?,
    val required: Boolean?,
    val requiredForGraduation: Boolean?,
    val assignmentHeader: AssignmentHeader?,
    val submission: Submission?,
    val assignmentContent: AssignmentContent?
)

data class AssignmentHeader(
    val header: String?
)

data class AssignmentContext(
    val contextCode: String?,
    val name: String?
)

data class Submission(
    val studentEnrollmentId: Int?,
    val date: String?,
    val notes: String?,
    val submissionUrlList: List<SubmissionUrl>,
    val submissionCommentList: List<SubmissionComment>,
    val submissionGrade: SubmissionGrade?
)

data class SubmissionGrade(
    val date: String?,
    val grade: String?
)

data class AssignmentContent(
    val content: String?
)

data class SubmissionComment(
    val date: String?,
    val comment: String?,
    val author: UserAccount?,
    val isPcCase: Boolean?
)

data class SubmissionUrl(
    val url: String?,
    val title: String?
)

data class CourseworkResponse(
    val assignment: Assignment?
)