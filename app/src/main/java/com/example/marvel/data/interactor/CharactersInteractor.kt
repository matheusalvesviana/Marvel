package com.example.marvel.data.interactor

import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.model.Response
import io.reactivex.Observable

class CharactersInteractor constructor(private val repository: CharactersRepository) {

    fun execute(limit: Int): Observable<Response> {
        return repository.characters(
            "<TS>",
            "<API_KEY>",
            "<HASH>",
            limit
        )
    }
}