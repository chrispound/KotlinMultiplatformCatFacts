package com.slug.catfacts

import com.slug.catfacts.kmm.shared.cache.Database
import com.slug.catfacts.kmm.shared.cache.DatabaseDriverFactory
import com.slug.catfacts.kmm.shared.network.CatFactApi
import org.lighthousegames.logging.logging

class CatFactSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = CatFactApi()
    private val log = logging("CatFacts")

    suspend fun getCatFact(forceReload: Boolean, todaysFact: CatFact?): List<CatFact> {
        val cachedFacts = database.getAllFacts()
        log.d {cachedFacts.toString()}
        //TODO: Handle forced reloads
        //TODO: 1 fact per day
        return if(cachedFacts.isNotEmpty() && !forceReload){
            log.d { "Using cached Facts"}
            cachedFacts
        } else {
            log.d {"Loading new fact"}
            api.getCatFact().let {
                log.d {it.toString()}
                database.insertCatFact(it)
                mutableListOf(it) + cachedFacts
            }
        }
    }
}