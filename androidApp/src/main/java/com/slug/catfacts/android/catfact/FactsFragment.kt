package com.slug.catfacts.android.catfact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        viewModel.loadNewFact()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.catFacts.collect {
                    if(it.isNotEmpty()) {
                        Log.d("CatFactsForTheSoul", it.toString())
                        val factTV = view.findViewById<TextView>(R.id.fact)
                        factTV.text = it[0].fact
                        Log.d("CatFacts", it.toString())
                    }
                }
            }
        }

    }
}
