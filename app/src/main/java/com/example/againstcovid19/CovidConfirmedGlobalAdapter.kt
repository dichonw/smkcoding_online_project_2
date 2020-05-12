package com.example.againstcovid19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_confirmed_global_item.*

class CovidConfirmedGlobalAdapter(private val context: Context, private val items: List<CovidConfirmedGlobalItem>, private val listener: (CovidConfirmedGlobalItem)-> Unit) :
    RecyclerView.Adapter<CovidConfirmedGlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_confirmed_global_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: CovidConfirmedGlobalItem, listener: (CovidConfirmedGlobalItem) -> Unit) {
            angkaPositif.text = item.totalConfirmed
            containerView.setOnClickListener { listener(item) }
        }
    }
}