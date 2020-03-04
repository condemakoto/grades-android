package com.trilogy.bootcampspot.di

import androidx.room.Room
import com.trilogy.bootcampspot.data.datasource.BcsDataSource
import com.trilogy.bootcampspot.data.datasource.SettingsDataSource
import com.trilogy.bootcampspot.data.datasource.UserDataSource
import com.trilogy.bootcampspot.data.datasource.db.AppDatabase
import com.trilogy.bootcampspot.data.datasource.local.LocalSettingsDataSource
import com.trilogy.bootcampspot.data.datasource.local.LocalUserDataSource
import com.trilogy.bootcampspot.data.datasource.remote.RemoteBcsDataSource
import com.trilogy.bootcampspot.data.mapper.EnrollmentToEnrollmentInfoMapper
import com.trilogy.bootcampspot.data.net.ApiProvider
import com.trilogy.bootcampspot.data.repository.DefaultBcsRepository
import com.trilogy.bootcampspot.domain.mapper.CourseworkModelMapper
import com.trilogy.bootcampspot.domain.mapper.EnrollmentInfoDataToDomainMapper
import com.trilogy.bootcampspot.domain.repository.BcsRepository
import com.trilogy.bootcampspot.domain.usecase.*
import com.trilogy.bootcampspot.view.bridge.LoginBridge
import com.trilogy.bootcampspot.view.dashboard.DashboardViewModel
import com.trilogy.bootcampspot.view.dashboard.generalinfo.GeneralInfoViewModel
import com.trilogy.bootcampspot.view.dashboard.submission.SubmissionsViewModel
import com.trilogy.bootcampspot.view.forgotpassword.ForgotPasswordViewModel
import com.trilogy.bootcampspot.view.grades.GradesViewModel
import com.trilogy.bootcampspot.view.onboarding.login.LoginViewModel
import com.trilogy.bootcampspot.view.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { GeneralInfoViewModel(get(), get()) }
    viewModel { SubmissionsViewModel(get()) }
    viewModel { GradesViewModel(get(), get()) }
}

val domainModule = module {
    factory { LoginUseCase(get(), get(), get()) }
    factory { RequestPasswordResetUseCase(get(), get(), get()) }
    factory { GetUserInfoUseCase(get(), get(), get()) }
    factory { GetUserAccountUseCase(get(), get(), get()) }
    factory { GetGradesUseCase(get(), get(), get()) }
    factory { GetDashboardUseCase(get(), get(), get()) }
    factory { GetEnrollmentIdUseCase(get(), get(), get()) }
    factory { CheckUserSignedInUseCase(get(), get(), get()) }
    factory { LogoutUseCase(get(), get(), get(), get()) }
    factory { GetGeneralInfoUseCase(get(), get(), get(), get()) }
    factory { GetEnrollmentInfoUseCase(get(), get(), get()) }
    factory { GetCourseworkUseCase(get(), get(), get()) }
}

val dataModule = module {
    single<BcsRepository> { DefaultBcsRepository(get(), get(), get(), get(), get(), get(), get()) }
    single<BcsDataSource> { RemoteBcsDataSource(get()) }
    single<SettingsDataSource> { LocalSettingsDataSource(androidContext()) }
    single<UserDataSource> { LocalUserDataSource(androidContext()) }
    single { EnrollmentToEnrollmentInfoMapper() }
    single { EnrollmentInfoDataToDomainMapper() }
    single { CourseworkModelMapper() }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "bcs-database").build()
    }
    single {
        val appDatabase: AppDatabase = get()
        appDatabase.enrollmentInfoDao()
    }

    single { ApiProvider(androidContext(), get(), get(), get()) }
    single { LoginBridge(androidContext()) }
    single {
        val provider: ApiProvider = get()
        provider.bcsApi
    }
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }
    single { CoroutineScope(Dispatchers.IO) }
}
