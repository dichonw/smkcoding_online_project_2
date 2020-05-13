package com.example.againstcovid19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_deaths_global_item.*

class CovidDeathsGlobalAdapter(private val context: Context, private val items: List<CovidDeathsGlobalItem>, private val listener: (CovidDeathsGlobalItem)-> Unit) :
    RecyclerView.Adapter<CovidDeathsGlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_deaths_global_item,
            parent, false))

    override fun getItemCount(): Int {
        val batasDeaths = 1
        when{
            items.size > batasDeaths ->
                return batasDeaths
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
        fun bindItem(item: CovidDeathsGlobalItem, listener: (CovidDeathsGlobalItem) -> Unit) {
            angkaMeninggal.text = item.totalDeaths
            containerView.setOnClickListener { listener(item) }
        }
    }
}