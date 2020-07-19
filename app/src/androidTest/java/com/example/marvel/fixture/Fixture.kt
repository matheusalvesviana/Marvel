package com.example.marvel.fixture

import com.example.marvel.model.Response
import com.google.gson.Gson

object Fixture {

    fun getCharactersResponse(fileName: String): Response {
        return Gson().fromJson(readJson(fileName), Response::class.java)
    }

    private fun readJson(fileName: String) = readFile("json/$fileName.json")

    private fun readFile(filePath: String) = javaClass.classLoader?.let { classLoader ->
        classLoader.getResourceAsStream(filePath)
            .bufferedReader()
            .use { it.readText() }
    }
}