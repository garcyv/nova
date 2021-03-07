package com.example.nova

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nova.pojo.Data
import com.example.nova.pojo.DataDetail

@Database(entities = [Data::class, DataDetail::class], version = 1)
abstract class DataBase: RoomDatabase()  {
    abstract fun getDataDao(): IDao
    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        private const val  DATA_BASE_NAME = "data_db"

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    DataBase::class.java,
                    DATA_BASE_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}