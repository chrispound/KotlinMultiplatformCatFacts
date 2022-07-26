package com.slug.catfacts.android

import android.app.Application
import com.slug.catfacts.CatFactSDK
import com.slug.catfacts.kmm.shared.cache.DatabaseDriverFactory

class CatFactsApplication: Application() {

    lateinit var catFactSDK: CatFactSDK

    override fun onCreate() {
        super.onCreate()
        catFactSDK = CatFactSDK(DatabaseDriverFactory(this))
    }
}