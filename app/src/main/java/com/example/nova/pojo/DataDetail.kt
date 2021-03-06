package com.example.nova.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "datadetail_table")
data class DataDetail (
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price : Long,
    @SerializedName("image")
    val imageLink: String,
    @SerializedName("lastprice")
    val lastprice: Long,
    @SerializedName("credit")
    val credit: Boolean
)