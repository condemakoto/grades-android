package com.trilogy.bootcampspot.data.net

import android.content.Context
import com.conde.kun.core.domain.BaseError
import com.conde.kun.core.error.BusinessErrorException
import com.conde.kun.core.error.NoInternetException
import com.conde.kun.core.error.ServerErrorException
import com.conde.kun.core.error.UnauthorizedException
import com.conde.kun.core.util.isOnline
import com.google.gson.*
import com.trilogy.bootcampspot.data.datasource.SettingsDataSource
import com.trilogy.bootcampspot.domain.usecase.LogoutUseCase
import com.trilogy.bootcampspot.domain.usecase.observer.MockDisposableSingleObserver
import com.trilogy.bootcampspot.view.bridge.LoginBridge
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*


class ApiProvider(
    context: Context,
    settingsDataSource: SettingsDataSource,
    loginBridge: LoginBridge,
    logoutUseCase: LogoutUseCase
) {


    private val API_BASE_URL: String = "http://ec2-34-229-142-114.compute-1.amazonaws.com:8080/"
    val bcsApi: BcsApi

    init {
        val gsonBuilder = GsonBuilder()

        gsonBuilder.registerTypeAdapter(Calendar::class.java, object : JsonDeserializer<Calendar> {
            override fun deserialize(
                json: JsonElement,
                typeOf: Type,
                context: JsonDeserializationContext
            ): Calendar {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = json.asJsonPrimitive.asLong * 1000
                return calendar
            }
        })

        val okHttpClient = getOkHttpClient(context, settingsDataSource, loginBridge, logoutUseCase)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        bcsApi = retrofitBuilder.create(BcsApi::class.java)

    }

    private fun getOkHttpClient(
        context: Context,
        settingsDataSource: SettingsDataSource,
        loginBridge: LoginBridge,
        logoutUseCase: LogoutUseCase
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            if (!isOnline(context)) {
                throw NoInternetException()
            }

            val original = chain.request()
            val requestBuilder = original.newBuilder()
            settingsDataSource.getToken()?.let {
                requestBuilder.addHeader("authToken", it)
            }

            chain.proceed(requestBuilder.build())

        }

        builder.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code() == 401) {
                settingsDataSource.setToken(null)
                logoutUseCase.execute(MockDisposableSingleObserver(), null)
                loginBridge.showLogin()
                throw UnauthorizedException()
            }

            if (response.code() == 400) {
                val gson = Gson()

                response.body()?.let {
                    val error: BaseError
                    try {
                        error = gson.fromJson(it.charStream(), BaseError::class.java)
                    } catch (ex: Exception) {
                        throw ServerErrorException()
                    }
                    throw BusinessErrorException(error)
                }
            }

            response
        }

        return builder.build()
    }

}