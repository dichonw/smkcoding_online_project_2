package com.example.againstcovid19

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.againstcovid19.Model.NewsPortalModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news_portal.*
import kotlinx.android.synthetic.main.fragment_profile.*


class NewsPortalFragment : Fragment() {

    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTeman: ArrayList<NewsPortalModel>

//    lateinit var listTeman: ArrayList<NewsPortal>
//    private fun simulasiDataTeman() { //ini sudah tidak digunakan lagi
//        listTeman = ArrayList()
//        listTeman.add(
//            NewsPortal(
//                "Fakhry", "fakhry@smkcoding.id",
//                "081123123123", "Malang"
//            )
//        )
//        listTeman.add(
//            NewsPortal(
//                "Ahmad", "ahmad@smkcoding.id",
//                "085123123123", "Malang"
//            )
//        )
//    }

//    private fun tampilTeman() { //ini sudah tidak digunakan lagi
//        rv_listNewsPortal.layoutManager = LinearLayoutManager(activity)
//        //rv_listMyFriends.adapter = MyFriendAdapter(activity!!, listTeman)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

        fab.setOnClickListener {
            val intent = Intent(getActivity(), NewsPortalActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(
                    getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Inisialisasi ArrayList
                dataTeman = java.util.ArrayList<NewsPortalModel>()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val teman = snapshot.getValue(NewsPortalModel::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    teman?.key = snapshot.key.toString()
                    dataTeman.add(teman!!)
                }
                //Memasang Adapter pada RecyclerView
                rv_listNewsPortal.layoutManager = LinearLayoutManager(context)
                rv_listNewsPortal.adapter = NewsPortalAdapter(context!!, dataTeman)
                Toast.makeText(
                    getContext(), "Data Berhasil Dimuat",
                    Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}






