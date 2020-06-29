package com.example.againstcovid19

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Button
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_profile.*
import android.widget.Toast
import android.R.string.cancel
import android.app.Dialog
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.news_portal_item.*


class ProfileFragment : Fragment() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        btn_logout.setOnClickListener { logOut() }

    }

    private fun setData(){
        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Auth
        var user = auth?.currentUser
        //Cek apakah sudah login atau belum
        if (auth!!.currentUser == null) {
//            var intent = Intent(context, LoginActivity::class.java)
//            startActivity(intent)

        } else {
            txtUser.text = user?.displayName
            Log.d("dicho", user?.displayName)

        }
    }

    private fun logOut() {
        val mAlertDialog = AlertDialog.Builder(context!!)
        mAlertDialog.setTitle("Logout")
        mAlertDialog.setMessage("Apakah anda yakin ingin keluar?")
        mAlertDialog.setPositiveButton("Ya"){dialog,  id ->
            FirebaseAuth.getInstance().signOut()
            val i = Intent(
                activity, LoginActivity::class.java
            )
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            startActivity(i)
        }
        mAlertDialog.setNegativeButton("Tidak"){dialog,  id ->
            Toast.makeText(context,"Logout dibatalkan", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.show()


    }
}



