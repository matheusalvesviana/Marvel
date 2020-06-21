package com.example.marvel.di

import com.example.marvel.BuildConfig
import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.data.interactor.CharactersInteractor
import com.example.marvel.data.repository.CharactersRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface Injection {
    val module: Module
        get() = module {
            factory { provideCharactersInteractor(get()) }
        }

    fun provideCharactersInteractor(charactersRepository: CharactersRepository): CharactersInteractor
    fun provideCharactersRepository(charactersRepository: CharactersRepository): CharactersRepository
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit
}

class InjectionImpl : Injection {
    override fun provideCharactersInteractor(charactersRepository: CharactersRepository): CharactersInteractor {
        return CharactersInteractor(charactersRepository)
    }

    override fun provideCharactersRepository(charactersRepository: CharactersRepository): CharactersRepository {
        return CharactersRepositoryImpl(charactersRepository)
    }

    override fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
       return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}