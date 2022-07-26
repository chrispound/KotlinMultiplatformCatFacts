package com.slug.catfacts.android.catfact

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slug.catfacts.CatFactSDK
import com.slug.catfacts.kmm.shared.cache.DatabaseDriverFactory

class FactsViewModelFactory(private val sdk: CatFactSDK): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CatFactSDK::class.java).newInstance(sdk)
    }
}