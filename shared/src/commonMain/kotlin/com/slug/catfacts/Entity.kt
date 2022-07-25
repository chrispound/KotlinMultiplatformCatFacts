package com.slug.catfacts


import kotlinx.serialization.Serializable


@Serializable
data class CatFact(
    val fact: String,
    val favorite: Boolean
)

@Serializable
data class CatFactResponse(
   val data: List<String>
)
