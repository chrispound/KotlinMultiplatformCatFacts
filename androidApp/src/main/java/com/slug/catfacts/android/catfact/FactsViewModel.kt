package com.slug.catfacts.android.catfact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slug.catfacts.CatFact
import com.slug.catfacts.CatFactSDK
import com.slug.catfacts.kmm.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FactsViewModel(private val catFactSDK: CatFactSDK) : ViewModel() {

    private val _mutableCatFacts = MutableStateFlow<List<CatFact>>(emptyList())
    val catFacts = _mutableCatFacts.asStateFlow()

    fun loadFacts() {
        viewModelScope.launch {
            val facts = catFactSDK.getCatFact(false, null)
            _mutableCatFacts.emit(facts)
        }
    }
}