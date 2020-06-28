package com.example.againstcovid19

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

//    private var auth: FirebaseAuth? = null
//    private val RC_SIGN_IN = 1

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 9001
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        progress.visibility = View.GONE
//        login.setOnClickListener(this)
//        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Auth
//        //Cek apakah sudah login atau belum
//        if (auth!!.currentUser == null) {
//        } else {
//        //Jika sudah login langsung dilempar ke MainActivity
//            intent = Intent(applicationContext,
//                MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        auth = FirebaseAuth.getInstance()

        btn_login_google.setOnClickListener(this)
        btn_login_facebook.setOnClickListener(this)

        if (auth!!.currentUser != null) {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
            finish()
        } else {
            Toast.makeText(this, "Belum Login", Toast.LENGTH_SHORT).show()
        }

    }
//    override fun onActivityResult(
//        requestCode: Int,
//        resultCode: Int,
//        data: Intent?
//    ) {
//        super.onActivityResult(requestCode, resultCode, data)
//        // RC_SIGN_IN = kode permintaan yang diberikan ke startActivityForResult
//        if (requestCode == RC_SIGN_IN) {
//            //Jika Berhasil masuk
//            if (resultCode == Activity.RESULT_OK) {
//                Toast.makeText(this, "Login Berhasil",
//                    Toast.LENGTH_SHORT).show()
//                intent = Intent(applicationContext,
//                    MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else { //Jika gagal login
//                progress.visibility = View.GONE
//                Toast.makeText(this, "Login Dibatalkan",
//                    Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else Toast.makeText(this, "Login Gagal...", Toast.LENGTH_SHORT).show()
        }
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


//    override fun onClick(v: View?) {
//        // Statement program untuk login/masuk
//        startActivityForResult(
//            AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
//                .setIsSmartLockEnabled(false)
//                .build(),
//            RC_SIGN_IN)
//        progress.visibility = View.VISIBLE
//    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_google -> googleLogin()
            R.id.btn_login_facebook -> facebookLogin()
        }
    }

    private fun googleLogin() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(
                    AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(true)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        btn_login_facebook.setReadPermissions("email", "public_profile")
        btn_login_facebook.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val move = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(move)
                finish()
            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity, "Batal Login...", Toast.LENGTH_SHORT).show()
            }


            override fun onError(error: FacebookException?) {
                Toast.makeText(this@LoginActivity, "Gagal Login...", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

