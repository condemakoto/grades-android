package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.datasource.SettingsDataSource
import com.trilogy.bootcampspot.data.datasource.db.EnrollmentInfoDAO
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogoutUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val dataSource: SettingsDataSource,
    private val enrollmentInfoDAO: EnrollmentInfoDAO

) :
    SingleUseCase<Boolean, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<Boolean> {
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.setToken(null)
            enrollmentInfoDAO.deleteAll()
        }
        return Single.just(true)
    }
}