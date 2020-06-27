package com.example.marvel.data.repository

import com.example.marvel.data.datasource.CharactersCloudRepository
import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.model.Response
import io.reactivex.Observable

class CharactersRepositoryImpl constructor(
    private val cloud: CharactersCloudRepository
) : CharactersRepository {
    override fun characters(ts: String, apikey: String, hash: String): Observable<Response> {
       val  query = mapOf<String, String>(
            "ts" to ts,
            "apikey" to apikey,
            "hash" to hash
        )
        return cloud.characters(query)
    }
}