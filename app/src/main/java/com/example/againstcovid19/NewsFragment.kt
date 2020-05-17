package com.example.againstcovid19

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
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

    lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWebView = webView
        mWebView.loadUrl("https://news.google.com/topics/CAAqBwgKMPe-mAswwMmwAw?hl=id&gl=ID&ceid=ID%3Aid")
        val webSetting = mWebView.settings
        webSetting.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()
    }
}
