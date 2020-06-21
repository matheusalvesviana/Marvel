package com.example.marvel.data.datasource

import com.example.marvel.model.Response
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET


interface CharactersRepository {

    @GET("/v1/public/characters")
    fun characters(
        @Field("ts") ts: String,
        @Field("apikey") apikey: String,
        @Field("hash") hash: String
    ): Observable<Response>
}