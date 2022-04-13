package com.limuealimi.newsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.limuealimi.newsapp.BuildConfig
import com.limuealimi.newsapp.data.network.ArticlesPagingSource
import com.limuealimi.newsapp.data.network.AuthInterceptor
import com.limuealimi.newsapp.data.network.api.ApiService
import com.limuealimi.newsapp.data.repository.MainRepository
import com.limuealimi.newsapp.data.repository.MainRepositoryImpl
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCase
import com.limuealimi.newsapp.domain.usecase.ArticleCardUseCaseImpl
import com.limuealimi.newsapp.presentation.home.HomeViewModel
import com.limuealimi.newsapp.utils.DefaultDispatcherProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModels = module {
    viewModel {
        HomeViewModel(get())
    }
}

val apiModule = module {
    single<HttpLoggingInterceptor>() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        loggingInterceptor
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .addInterceptor(
                ChuckerInterceptor.Builder(get<Context>())
                    .collector(ChuckerCollector(get<Context>()))
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}

val singletonModule = module {
    single<ArticlesPagingSource> { get() }

    single<MainRepository> {
        MainRepositoryImpl(get<ApiService>())
    }
    single<ArticleCardUseCase> {
        ArticleCardUseCaseImpl(get<MainRepository>(), DefaultDispatcherProvider())
    }
}

