package com.example.marvel.data.repository

import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.model.Response
import io.reactivex.Observable

class CharactersRepositoryImpl constructor(
    private val cloud: CharactersRepository
) : CharactersRepository {
    override fun characters(ts: String, apikey: String, hash: String): Observable<Response> {
        return cloud.characters(ts, apikey, hash)
    }
}