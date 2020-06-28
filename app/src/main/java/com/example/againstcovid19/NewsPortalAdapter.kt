package com.example.againstcovid19

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.againstcovid19.Model.NewsPortalModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_portal_item.*

class NewsPortalAdapter (private val context: Context, private val list: ArrayList<NewsPortalModel>) :
    RecyclerView.Adapter<NewsPortalAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.news_portal_item, parent, false)
    )
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))

        //Mengambil Value di RecyclerView berdasarkan Posisi Tertentu
        val Nama: String? = list.get(position).nama
        val Email: String? = list.get(position).email
        val Telp: String? = list.get(position).telp
        val Alamat: String? = list.get(position).alamat

        //Memasukan Nilai/Value ke dalam View (TextView: Nama, Email, Telp, Alamat)
        holder.txtFriendName.setText("$Nama")
        holder.txtFriendEmail.setText("$Email")
        holder.txtFriendTelp.setText("$Telp")
        holder.txtFriendAlamat.setText("$Alamat")

        //Deklarasi objek dari Interfece
//        val listener: dataListener? = null
        lateinit var ref : DatabaseReference
        lateinit var auth: FirebaseAuth

        holder.cardview_crud.setOnLongClickListener(View.OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        /* Berpindah Activity pada halaman layout updateData dan
                       mengambil data pada listMahasiswa, berdasarkan posisinya untuk dikirim pada
                       activity selanjutnya
                        */
                        val bundle = Bundle()
                        bundle.putString("dataNama", list.get(position).nama)
                        bundle.putString("dataEmail", list.get(position).email)
                        bundle.putString("dataTelp", list.get(position).telp)
                        bundle.putString("dataAlamat", list.get(position).alamat)
                        bundle.putString("getPrimaryKey", list.get(position).key)

                        val intent = Intent(view.context, NewsUpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Teman")
                                .child(list.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        context, "Data Berhasil Dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    }
                }
            }
                            alert.create()
                            alert.show()
                            true
                })

            }
            class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
                LayoutContainer {
                fun bindItem(item: NewsPortalModel) {
                    txtFriendName.text = item.nama
                    txtFriendEmail.text = item.email
                    txtFriendTelp.text = item.telp
                    txtFriendAlamat.text = item.alamat
                }
            }
        }
