package com.slug.catfacts

import com.slug.catfacts.kmm.shared.cache.Database
import com.slug.catfacts.kmm.shared.cache.DatabaseDriverFactory
import com.slug.catfacts.kmm.shared.network.CatFactApi

class CatFactSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = CatFactApi()

    suspend fun getCatFact(forceReload: Boolean, todaysFact: CatFact?): List<CatFact> {
        val cachedFacts = database.getAllFacts()
        //TODO: Handle forced reloads
        //TODO: 1 fact per day
        return if(cachedFacts.isNotEmpty() && cachedFacts.contains(todaysFact) && !forceReload){
            cachedFacts
        } else {
            api.getCatFact().let {
                database.insertCatFact(it)
                mutableListOf(it) + cachedFacts
            }
        }
    }
}