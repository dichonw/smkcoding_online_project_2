package com.example.againstcovid19.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_friend")
data class NewsPortalModel(
    var nama: String,
    var email: String,
    var telp: String,
    var alamat: String,
    @PrimaryKey var key: String
){


//    fun getKey() {
//    }
//
//    fun setKey(key: String?) {
//
//    }

    constructor() : this("","","","",""
    )
}

