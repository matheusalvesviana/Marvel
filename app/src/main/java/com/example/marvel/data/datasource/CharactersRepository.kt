package com.example.marvel.data.datasource

import com.example.marvel.model.Response
import io.reactivex.Observable

interface CharactersRepository {
    fun characters(ts: String, apikey: String, hash: String): Observable<Response>
}