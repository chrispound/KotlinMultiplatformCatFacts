package com.slug.catfacts.android.catfact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.slug.catfacts.android.CatFactsApplication
import com.slug.catfacts.android.R
import kotlinx.coroutines.launch

class FactsFragment : Fragment() {

    private val viewModel: FactsViewModel by viewModels { FactsViewModelFactory((activity?.application as CatFactsApplication).catFactSDK) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_facts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadFacts()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.catFacts.collect {
                    Log.d("CatFacts", it.toString())
                }
            }
        }

    }
}
