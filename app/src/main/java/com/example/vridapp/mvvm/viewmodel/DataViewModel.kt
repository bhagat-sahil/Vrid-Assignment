package com.example.vridapp.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vridapp.mvvm.model.dataresponse.VridResponse
import com.example.vridapp.mvvm.model.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private var repository: Repository) : ViewModel() {

    private var firstRequest: Boolean = true
    private var pageCnt: Int = 1
    private var isLoading: Boolean = false
    val data = MutableLiveData<VridResponse?>()

    init {
        getAllData()
    }
    fun getAllData() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllData(10, pageCnt)
            isLoading = false

            if (response.isSuccessful) {
                Log.i("RqstCnt", "cnt")
                pageCnt++

                val newData = response.body()
                if (firstRequest) {
                    firstRequest = false
                    data.postValue(newData)
                } else {
                    val oldData = data.value
                    oldData?.addAll(newData!!)
                    data.postValue(oldData)
                }
            } else {

                Log.e("DataViewModel", "Error fetching data: ${response.errorBody()}")
            }
        }
    }
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

            Log.i("lastitem", lastVisibleItem.toString())
            Log.i("totalitem", totalItemCount.toString())

            if ( lastVisibleItem == totalItemCount-1 )
            {
                getAllData()
            }
        }
    }
}