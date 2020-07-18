package com.example.marvel.di

import com.example.marvel.viewmodel.MainViewModel
import com.example.marvel.data.datasource.CharactersCloudRepository
import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.data.interactor.CharactersInteractor
import com.example.marvel.data.repository.CharactersRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.viewmodel.dsl.viewModel

interface Injection {
    val module: Module
        get() = module {
            factory { provideCharactersInteractor(get()) }
            factory { provideCharactersRepository(get()) }
            factory { provideCharactersCloudRepository(get()) }
            single { provideOkhttpClient(get()) }
            factory { provideRetrofit(get()) }
            factory { provideHttpLoggingInterceptor() }
            viewModel { MainViewModel(get()) }
        }

    fun provideCharactersInteractor(charactersRepository: CharactersRepository): CharactersInteractor
    fun provideCharactersRepository(charactersCloudRepository: CharactersCloudRepository): CharactersRepository
    fun provideCharactersCloudRepository(retrofit: Retrofit): CharactersCloudRepository
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
}

class InjectionImpl : Injection {
    override fun provideCharactersInteractor(charactersRepository: CharactersRepository): CharactersInteractor {
        return CharactersInteractor(charactersRepository)
    }

    override fun provideCharactersRepository(charactersRepository: CharactersCloudRepository): CharactersRepository {
        return CharactersRepositoryImpl(charactersRepository)
    }

    override fun provideCharactersCloudRepository(retrofit: Retrofit): CharactersCloudRepository {
        return retrofit.create(CharactersCloudRepository::class.java)
    }

    override fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    override fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build(); }

    override fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }
}