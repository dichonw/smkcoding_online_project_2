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
import androidx.appcompat.app.AlertDialog


class ProfileFragment : Fragment() {

    private val auth: FirebaseAuth? = null

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
        btn_logout.setOnClickListener { signOut() }


    }

    private fun signOut() {
//        FirebaseAuth.getInstance().signOut()
//        val intent = Intent(context, LoginActivity::class.java)
//        startActivity(intent)
//        AuthUI.getInstance().signOut(activity!!) // this is the issue
//            .addOnCompleteListener {
//                // user is now signed out
//            }
        FirebaseAuth.getInstance().signOut()
        val i = Intent(
            activity, LoginActivity::class.java
        )
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        startActivity(i)

    }
}



