package com.trilogy.bootcampspot.data.repository

import com.trilogy.bootcampspot.data.datasource.BcsDataSource
import com.trilogy.bootcampspot.data.datasource.SettingsDataSource
import com.trilogy.bootcampspot.data.datasource.UserDataSource
import com.trilogy.bootcampspot.data.datasource.db.EnrollmentInfoDAO
import com.trilogy.bootcampspot.data.exception.InvalidCredentialsException
import com.trilogy.bootcampspot.data.mapper.EnrollmentToEnrollmentInfoMapper
import com.trilogy.bootcampspot.data.net.response.*
import com.trilogy.bootcampspot.domain.mapper.CourseworkModelMapper
import com.trilogy.bootcampspot.domain.mapper.EnrollmentInfoDataToDomainMapper
import com.trilogy.bootcampspot.domain.model.CourseworkModel
import com.trilogy.bootcampspot.domain.model.EnrollmentInfo
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import io.reactivex.Single

class DefaultBcsRepository(
    private val bcsDataSource: BcsDataSource,
    private val settingsDataSource: SettingsDataSource,
    private val userDataSource: UserDataSource,
    private val enrollmentInfoDAO: EnrollmentInfoDAO,
    private val enrollmentToEnrollmentInfoMapper: EnrollmentToEnrollmentInfoMapper,
    private val enrollmentInfoDataToDomainMapper: EnrollmentInfoDataToDomainMapper,
    private val courseworkModelMapper: CourseworkModelMapper
) :
    BcsRepository {

    override fun login(username: String, password: String): Single<Boolean> {
        return bcsDataSource.login(username, password).map { loginResponse: LoginResponse ->
            if (loginResponse.errorCode != null && loginResponse.errorCode.equals("INVALID_CREDENTIALS")) {
                throw InvalidCredentialsException()
            }
            settingsDataSource.setToken(loginResponse.authenticationInfo?.authToken)
            loginResponse.authenticationInfo?.authToken != null
        }
    }

    override fun requestPasswordReset(username: String): Single<Boolean> {
        return bcsDataSource.requestPasswordReset(username).map { response: BaseResponse ->
            response.success?.let {
                true
            }
            false
        }
    }

    override fun getUserInfo(): Single<MeResponse> {
        return bcsDataSource.getUserInfo().map { response: MeResponse ->
            response.userAccount?.let { userDataSource.storeUserAccount(it) }
            response.enrollments?.let {
                if (it.isNotEmpty()) {
                    userDataSource.storeEnrollmentId(it[0].id!!)
                    enrollmentInfoDAO.insert(enrollmentToEnrollmentInfoMapper.map(it[0]))
                }
            }
            response
        }
    }

    override fun getEnrollmentId(): Single<Int> {
        return userDataSource.getEnrollmentId()
    }

    override fun getDashboard(enrollmentId: Int): Single<DashboardResponse> {
        return bcsDataSource.getDashboard(enrollmentId)
    }

    override fun getUserAccount(): Single<UserAccount> {
        return userDataSource.getUserAccount()
    }

    override fun getGrades(enrollmentId: Int): Single<List<GradesResponse>> {
        return bcsDataSource.getGrades(enrollmentId)
    }

    override fun listEnrollmentInfo(): Single<List<EnrollmentInfo>> {
        return enrollmentInfoDAO.getAll()
            .map(enrollmentInfoDataToDomainMapper::mapAll)
    }

    override fun findCourseworkDetail(assignmentId: Int): Single<CourseworkModel> {
        return bcsDataSource.getCourseworkDetail(assignmentId).map(courseworkModelMapper::map)
    }
}