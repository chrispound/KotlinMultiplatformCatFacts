package com.slug.catfacts.android.catfact

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.slug.catfacts.CatFact
import com.slug.catfacts.android.R

class CatFactAdapter( private val onFavorite: OnFactFavorited) :
    RecyclerView.Adapter<CatFactAdapter.CatFactViewHolder>() {

    //TODO: DiffUtil
    var facts: List<CatFact> = emptyList()
        set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder {
        val view =
            LayoutInflater.from((parent.context)).inflate(R.layout.item_catfact, parent, false)
        return CatFactViewHolder(view, onFavorite)
    }

    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        holder.bind(facts[position])
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    inner class CatFactViewHolder(itemView: View, factFavorited: OnFactFavorited) :
        RecyclerView.ViewHolder(itemView) {

        private val fact: TextView = itemView.findViewById(R.id.fact)
        private val isFavorite: RadioButton = itemView.findViewById(R.id.isFavorite)

        init {
            isFavorite.setOnClickListener {
                factFavorited.onFavorited(facts[absoluteAdapterPosition])
            }
        }

        fun bind(catFact: CatFact) {
            fact.text = catFact.fact
            isFavorite.isChecked = catFact.favorite
        }
    }
}

interface OnFactFavorited {
    fun onFavorited(catFact: CatFact)
}