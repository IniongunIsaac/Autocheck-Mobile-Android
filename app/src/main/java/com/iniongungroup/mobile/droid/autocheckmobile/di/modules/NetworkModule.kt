package com.iniongungroup.mobile.droid.autocheckmobile.di.modules

import android.content.Context
import com.google.gson.Gson
import com.iniongungroup.mobile.droid.autocheckmobile.BuildConfig
import com.iniongungroup.mobile.droid.autocheckmobile.BuildConfig.BASE_URL
import com.iniongungroup.mobile.droid.autocheckmobile.di.scopes.AppScope
import com.iniongungroup.mobile.droid.autocheckmobile.remotedatasource.utils.RemoteDataSourceConstants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Isaac Iniongun on 23/06/2020.
 * For News App project.
 */

@Module
class NetworkModule {

    @Provides
    @AppScope
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { log ->
        Timber.e(log)
    }.apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    internal fun provideFile(context: Context): File = File (
        context.cacheDir, RemoteDataSourceConstants.OKHTTP_CACHE
    )

    @Provides
    internal fun provideCache(file: File): Cache = Cache (
        file, RemoteDataSourceConstants.OKHTTP_CACHE_SIZE.toLong()
    )

    @Provides
    internal fun provideOkhttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            cache(cache)
            followRedirects(true)
            followSslRedirects(true)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    internal fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    internal fun provideGsonAdapterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BASE_URL)
    }.build()

}