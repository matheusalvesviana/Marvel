package com.example.marvel.data.interactor

import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.model.Response
import io.reactivex.Observable

class CharactersInteractor constructor(private val repository: CharactersRepository) {

    fun execute(): Observable<Response> {
        return repository.characters(
            "1",
            "3f2c0a88325ecd1507a4f1f2b4031cb7",
            "a894a5dffe74446ccd90b8a4070a58d4"
        )
            .onErrorResumeNext { error: Throwable -> return@onErrorResumeNext error(error) }
    }
}