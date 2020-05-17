package com.example.againstcovid19.ListDataIndo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.againstcovid19.ListData.CovidGlobalListItem
import com.example.againstcovid19.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_covid_global_item.*
import kotlinx.android.synthetic.main.list_covid_indo_item.*

class CovidIndoListAdapter(private val context: Context, private val items: List<CovidIndoListItem>, private val listener: (CovidIndoListItem)-> Unit) :
    RecyclerView.Adapter<CovidIndoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.list_covid_indo_item,
            parent, false))

    override fun getItemCount(): Int {

        return items.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: CovidIndoListItem, listener: (CovidIndoListItem) -> Unit) {
            provinsi.text = item.attributes.provinsi
            angkaPositifProvinsi.text = item.attributes.kasusPosi.toString()
            angkaSembuhProvinsi.text = item.attributes.kasusSemb.toString()
            angkaMeninggalProvinsi.text = item.attributes.kasusMeni.toString()
            containerView.setOnClickListener { listener(item) }
        }
    }
}