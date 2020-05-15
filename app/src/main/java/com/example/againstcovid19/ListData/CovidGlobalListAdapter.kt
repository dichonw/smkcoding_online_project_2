package com.example.againstcovid19.ListData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.againstcovid19.CovidIndoItem
import com.example.againstcovid19.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_indo_item.*
import kotlinx.android.synthetic.main.covid_indo_item.angkaMeniggal
import kotlinx.android.synthetic.main.covid_indo_item.angkaPositif
import kotlinx.android.synthetic.main.covid_indo_item.angkaSembuh
import kotlinx.android.synthetic.main.covid_indo_item.negara
import kotlinx.android.synthetic.main.list_covid_global_item.*

class CovidGlobalListAdapter(private val context: Context, private val items: List<CovidGlobalListItem>, private val listener: (CovidGlobalListItem)-> Unit) :
    RecyclerView.Adapter<CovidGlobalListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.list_covid_global_item,
            parent, false))

    override fun getItemCount(): Int {

        return items.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: CovidGlobalListItem, listener: (CovidGlobalListItem) -> Unit) {
            negaraGlobal.text = item.attributes.countryRegion
            angkaPositifGlobal.text = item.attributes.confirmed.toString()
            angkaSembuhGlobal.text = item.attributes.recovered.toString()
            angkaMeniggalGlobal.text = item.attributes.deaths.toString()
            containerView.setOnClickListener { listener(item) }
        }
    }
}