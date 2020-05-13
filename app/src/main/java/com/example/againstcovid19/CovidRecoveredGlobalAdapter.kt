package com.example.againstcovid19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_recovered_global_item.*

class CovidRecoveredGlobalAdapter(private val context: Context, private val items: List<CovidRecoveredGlobalItem>, private val listener: (CovidRecoveredGlobalItem)-> Unit) :
    RecyclerView.Adapter<CovidRecoveredGlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_recovered_global_item,
            parent, false))
    override fun getItemCount(): Int {
        val batasRecovered = 1
        when{
            items.size > batasRecovered ->
                return batasRecovered
            else -> {
                return items.size
            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: CovidRecoveredGlobalItem, listener: (CovidRecoveredGlobalItem) -> Unit) {
            angkaSembuh.text = item.totalRecovered
            containerView.setOnClickListener { listener(item) }
        }
    }
}