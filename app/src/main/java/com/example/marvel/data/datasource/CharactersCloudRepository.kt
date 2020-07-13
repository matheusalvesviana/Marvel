package com.example.marvel.data.datasource

import com.example.marvel.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface CharactersCloudRepository {
    @GET("/v1/public/characters")
    fun characters(
        @QueryMap params: HashMap<String, Any>
    ): Observable<Response>
}