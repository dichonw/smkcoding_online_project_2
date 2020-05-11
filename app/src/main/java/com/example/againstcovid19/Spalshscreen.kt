package com.example.againstcovid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity

class Spalshscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalshscreen)

        Handler().postDelayed({
            finish()
            startActivity<MainActivity>()
        }, 2000)
    }
}
