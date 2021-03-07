package com.example.nova

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nova.pojo.Data
import com.example.nova.pojo.DataDetail
import kotlinx.coroutines.launch

class VViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    val allData: LiveData<List<Data>>
    val selectedData: MutableLiveData<Data> = MutableLiveData()
    val selectedIdData: MutableLiveData<Int> = MutableLiveData()

    init {
        val dataDao = DataBase.getDatabase(application).getDataDao()
        repository = Repository(dataDao)
        viewModelScope.launch {
            repository.getDataWithCoroutines()
        }
        allData = repository.listAllData
    }

    fun getDataDetail(id: Int): LiveData<DataDetail> {
        repository.getDataDetailsFromApi(id)
      return   repository.getDataDetails(id)

    }

    fun getFetchDataWithCoroutines(): LiveData<List<Data>> {
        return repository.liveData
    }

    fun selected(data: Data?) {

        selectedData.value = data
    }

    fun selectedId(idData: Int) {

        selectedIdData.value = idData
    }
}