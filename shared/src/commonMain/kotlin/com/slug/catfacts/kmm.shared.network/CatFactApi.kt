package com.slug.catfacts.kmm.shared.network

import com.slug.catfacts.CatFact
import com.slug.catfacts.CatFactResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class CatFactApi {

    companion object {
        private const val CATFACTS_URL = "https://meowfacts.herokuapp.com/"
    }
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCatFact(): CatFact {
        val response: CatFactResponse = httpClient.get(CATFACTS_URL)
        return CatFact(
            response.data[0],
            false
        )
    }
}

