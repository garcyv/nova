package com.example.nova

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nova.pojo.Data
import com.example.nova.pojo.DataDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val dataDao: IDao)  {
    private val services = Retrofit.retrofitInstance()
    val liveData = MutableLiveData<List<Data>>()
    val listAllData: LiveData<List<Data>> = dataDao.getAlldataFromDB()

    suspend fun insertAllData(listData: List<Data>){
        dataDao.insertAllData(listData)
    }

    fun getDataDetails(id: Int): LiveData<DataDetail> {
        return dataDao.getOneDataDetails(id)
    }
    //Funcion q utiliza coroutines
    suspend fun getDataWithCoroutines(){
        Log.d("REPOSITORY","USO CORoutines")
        try {
            val response = Retrofit.retrofitInstance().fetchDataCoroutines()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    insertAllData(it)
                }
                false -> Log.d("ERROR", " ${response.code()}: ${response.errorBody()}")
            }
        } catch (t:Throwable){
            Log.d("ERROR COR",t.message.toString())
        }
    }


    /*
 * Utilizando retrofit, va a buscar los detalles dado un 'id' y los guarda en local.
 */
    fun getDataDetailsFromApi(id: Int) = CoroutineScope(Dispatchers.IO).launch {

        val service = kotlin.runCatching { services.fetchOneData(id) }
        service.onSuccess {
            when(it.code()) {

                in 200..299 -> it.body()?.let { details ->
                    dataDao.insertOneDataDetails(details)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())

            }
        }

        service.onFailure {

            Log.e("ERROR", it.message.toString())

        }

    }

}