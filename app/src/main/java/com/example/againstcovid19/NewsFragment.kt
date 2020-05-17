package com.example.againstcovid19

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.againstcovid19.News.Article
import com.example.againstcovid19.News.News
import com.example.againstcovid19.News.NewsAdapter
import com.example.againstcovid19.dataNews.NewsService
import com.example.againstcovid19.dataNews.apiRequestNews
import com.example.againstcovid19.dataNews.httpClient
import com.example.againstcovid19.util.dismissLoading
import com.example.againstcovid19.util.showLoading
import com.example.againstcovid19.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private fun callApiNews(){
        showLoading(context!!, swipeNews)

        val httpClient = httpClient()
        val apiReq = apiRequestNews<NewsService>(httpClient)

        val call = apiReq.getNews()
        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                dismissLoading(swipeNews)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                dismissLoading(swipeNews)
//
                when{
                    response.isSuccessful->
                        when{
                            response.body()?.articles?.size != 0 ->
                                tampilNews(response.body()!!.articles)
                            else->{
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else->
                        tampilToast(context!!, "Gagal")
                }
            }
//            override fun onFailure(call: Call<List<NewsCovid>>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<List<NewsCovid>>,
//                response: Response<List<NewsCovid>>
//            ) {
//                dismissLoading(swipeNews)
//
//                when{
//                    response.isSuccessful->
//                        when{
//                            response.body()?.size != 0 ->
//                                tampilNews(response.body()!!)
//                            else->{
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }
//                    else->
//                        tampilToast(context!!, "Gagal")
//                }
//            }
        })
    }

    private fun tampilNews(nws: List<Article>){
        listNews.layoutManager = LinearLayoutManager(context)
        listNews.adapter = NewsAdapter(context!!, nws){
            val aq = it
            tampilToast(context!!, aq.title)

            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("abc", aq.url)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
