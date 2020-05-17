package com.example.againstcovid19

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_list_data.*


class InfoFragment : Fragment() {

    internal lateinit var myDialog : Dialog
    internal lateinit var button : Button
    internal lateinit var txt : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardview_covid.setOnClickListener {
            showDialogPengertian()
        }
        cardview_gejala.setOnClickListener {
            showDialogGejala()
        }
        cardview_pencegahan.setOnClickListener {
            showDialogPencegahan()
        }

    }
    fun showDialogPengertian(){
        myDialog = Dialog(context!!)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.pengertian_activity)
        myDialog.setTitle("Apa itu Covid-19?")

        txt = myDialog.findViewById(R.id.btn_ok)
        txt.isEnabled = true
        txt.setOnClickListener {
            Toast.makeText(context, "Covid-19", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        }
        myDialog.show()
    }
    fun showDialogGejala(){
        myDialog = Dialog(context!!)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.gejala_activity)
        myDialog.setTitle("Gejala")

        txt = myDialog.findViewById(R.id.btn_ok)
        txt.isEnabled = true
        txt.setOnClickListener {
            Toast.makeText(context, "Gejala", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        }
        myDialog.show()
    }
    fun showDialogPencegahan(){
        myDialog = Dialog(context!!)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.pencegahan_activity)
        myDialog.setTitle("Pencegahan")

        txt = myDialog.findViewById(R.id.btn_ok)
        txt.isEnabled = true
        txt.setOnClickListener {
            Toast.makeText(context, "Pencegahan", Toast.LENGTH_LONG).show()
            myDialog.cancel()
        }
        myDialog.show()
    }

}

