package com.example.againstcovid19.News

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.againstcovid19.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

class NewsAdapter(private val context: Context, private val items: List<Article>, private val listener: (Article)->Unit):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View):RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: Article, listener: (Article) -> Unit){
            txtHead.text = item.title
            txtBerita.text = item.source.name
            Glide.with(context).load(item.urlToImage).into(gambar)

            containerView.setOnClickListener{listener(item)}
        }
    }
}