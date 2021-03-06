package com.example.nova

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nova.pojo.Data
import com.example.nova.pojo.DataDetail

@Dao
interface IDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(mdataList: List<Data>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneDataDetails(details: DataDetail)

    @Query("SELECT * FROM data_table")
    fun getAlldataFromDB(): LiveData<List<Data>>

    @Query("SELECT * FROM datadetail_table WHERE id=:id")
    fun getOneDataDetails(id: Int): LiveData<DataDetail>
}