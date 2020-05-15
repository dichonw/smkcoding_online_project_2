package com.example.againstcovid19

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import kotlinx.android.synthetic.main.fragment_list_data.*


class ListDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardview_global.setOnClickListener {
            val move = Intent(context, GlobalListActivity::class.java)
            startActivity(move)
        }
        cardview_indo.setOnClickListener {
            val move = Intent(context, GlobalListActivity::class.java)
            startActivity(move)
        }
    }
}
