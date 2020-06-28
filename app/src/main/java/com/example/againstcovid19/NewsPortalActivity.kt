package com.example.againstcovid19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.againstcovid19.Model.NewsPortalModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_news_portal.*

class NewsPortalActivity : AppCompatActivity() {

    private var Nama: EditText? = null
    private var Email: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null
    private lateinit var button : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal)

        button = findViewById(R.id.btn_back_newsPortal) as ImageButton
        button.setOnClickListener(View.OnClickListener { this@NewsPortalActivity.finish() })

        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById<EditText>(R.id.email)
        Telp = findViewById<EditText>(R.id.telp)
        Alamat = findViewById<EditText>(R.id.alamat)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener{
            prosesSave()
        }
    }
    private fun prosesSave() {
        val getNama: String = Nama?.getText().toString()
        val getEmail: String = Email?.getText().toString()
        val getTelp: String = Telp?.getText().toString()
        val getAlamat: String = Alamat?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() &&
            getAlamat.isEmpty()) {
            //Jika kosong, maka akan menampilkan pesan singkat berikut ini.
            Toast.makeText(this@NewsPortalActivity,"Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show()
        } else {
            val teman = NewsPortalModel(getNama, getEmail, getTelp, getAlamat, getUserID)
            //struktur databasenya adalah: ID >> Teman >> Key >> Data
            ref.child(getUserID).child("Teman").push().setValue(teman).addOnCompleteListener {
                Toast.makeText(this, "Data Berhasil Disimpan",
                    Toast.LENGTH_SHORT).show()
            }
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

