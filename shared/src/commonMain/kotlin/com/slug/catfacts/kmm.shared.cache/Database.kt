package com.slug.catfacts.kmm.shared.cache

import com.slug.catfacts.CatFact
import comslugcatfactskmmsharedcache.Facts

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.clearAllFacts()
        }
    }

    internal fun getFavorites(): List<CatFact> {
        return dbQuery.selectAllFavorites(::mapCatFact).executeAsList()
    }

    internal fun getAllFacts(): List<CatFact> {
        return dbQuery.selectAllFavorites(::mapCatFact).executeAsList()
    }

    private fun mapCatFact(
        fact: String,
        favorite: Long
    ): CatFact {
        return CatFact(
            fact,
            favorite == 1L
        )
    }

    internal fun insertCatFact(
        fact: CatFact
    ) {
        dbQuery.insertFacts(
            fact.fact,
            if (fact.favorite) 1L else 0L
        )
    }
}