package com.example.nova.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data_table")
data class Data(val price: Long,
                       @PrimaryKey
                       val id: Int,
                       val name: String,
                       @SerializedName("image")
                       val imgSrc: String)