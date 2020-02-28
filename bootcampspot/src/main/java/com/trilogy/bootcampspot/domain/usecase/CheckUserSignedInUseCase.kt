package com.trilogy.bootcampspot.domain.usecase

import com.trilogy.bootcampspot.data.datasource.SettingsDataSource
import io.reactivex.Single

class CheckUserSignedInUseCase(
    threadExecutor: ThreadExecutor,
    mPostExecutionThread: PostExecutionThread,
    private val dataSource: SettingsDataSource
) : SingleUseCase<Boolean, Unit?>(threadExecutor, mPostExecutionThread) {

    override fun buildObservable(params: Unit?): Single<Boolean> {
        return Single.just(dataSource.getToken() != null)
    }
}