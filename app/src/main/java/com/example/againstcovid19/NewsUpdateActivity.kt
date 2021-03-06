package com.example.againstcovid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.againstcovid19.Model.NewsPortalModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_news_update.*
import org.jetbrains.anko.db.PRIMARY_KEY

class NewsUpdateActivity : AppCompatActivity() {

    //    //Deklarasi Variable
    private var namaBaru: EditText? = null
    private var emailBaru: EditText? = null
    private var telpBaru: EditText? = null
    private var alamatBaru: EditText? = null
    lateinit var update: Button
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNama: String? = null
    private var cekEmail: String? = null
    private var cekTelp: String? = null
    private var cekAlamat: String? = null
    private lateinit var buttonBackUpdate : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_update)

        buttonBackUpdate = findViewById(R.id.btn_back_newsUpdate) as ImageButton
        buttonBackUpdate.setOnClickListener(View.OnClickListener { this@NewsUpdateActivity.finish() })

        getSupportActionBar()?.setTitle("Update Data")
        namaBaru = findViewById(R.id.new_nama)
        emailBaru = findViewById(R.id.new_email)
        telpBaru = findViewById(R.id.new_telp)
        alamatBaru = findViewById(R.id.new_alamat)
        update = findViewById(R.id.update)

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();

        //Mengambil dari intent data yang akan di update
        update.setOnClickListener {
            //Mendapatkan Data Mahasiswa yang akan dicek
            cekNama = namaBaru?.getText().toString()
            cekEmail = emailBaru?.getText().toString()
            cekTelp = telpBaru?.getText().toString()
            cekAlamat = alamatBaru?.getText().toString()

            //Mengecek agar tidak ada data yang kosong, saat proses update
            if (isEmpty(cekNama) || isEmpty(cekEmail) || isEmpty(cekTelp) || isEmpty(cekAlamat)) {
                Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
            } else {
                /* Menjalankan proses update data. Method Setter digunakan untuk mendapakan data baru yang diinputkan User.*/
                val temanBaru = NewsPortalModel(cekNama!!, cekEmail!!, cekTelp!!, cekAlamat!!, "")
                val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                val getKey: String = getIntent().getStringExtra("getPrimaryKey").toString()
                database!!.child(getUserID).child("Teman")
                    .child(getKey).setValue(temanBaru)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                        finish();
                    }
            }
        }
    }
    private fun getData() {
        //Menampilkan data dari item yang dipilih sebelumnya
        val getNama: String = getIntent().getStringExtra("dataNama").toString()
        val getEmail: String = getIntent().getExtras()?.getString("dataEmail").toString()
        val getTelp: String = getIntent().getExtras()?.getString("dataTelp").toString()
        val getAlamat: String = getIntent().getExtras()?.getString("dataAlamat").toString()
        namaBaru?.setText(getNama);
        emailBaru?.setText(getEmail);
        telpBaru?.setText(getTelp);
        alamatBaru?.setText(getAlamat);
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()
    }
}



