package com.example.marvel.data.repository

import com.example.marvel.data.datasource.CharactersCloudRepository
import com.example.marvel.data.datasource.CharactersRepository
import com.example.marvel.model.Response
import io.reactivex.Observable

class CharactersRepositoryImpl constructor(
    private val cloud: CharactersCloudRepository
) : CharactersRepository {
    override fun characters(ts: String, apikey: String, hash: String, limit: Int): Observable<Response> {
        val query = hashMapOf(
            "ts" to ts,
            "apikey" to apikey,
            "hash" to hash,
            "limit" to limit
        )
        return cloud.characters(query)
    }
}