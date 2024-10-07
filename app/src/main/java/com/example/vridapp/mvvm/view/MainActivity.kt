package com.example.vridapp.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vridapp.R
import com.example.vridapp.mvvm.model.api.RetrofitInstance
import com.example.vridapp.mvvm.model.repo.Repository
import com.example.vridapp.mvvm.viewmodel.DataViewModel
import com.example.vridapp.mvvm.viewmodel.DataViewmodelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VridAdapter
    private lateinit var dataViewmodel: DataViewModel
    private lateinit var datamodelfactory: DataViewmodelProviderFactory
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inti()
        dataViewmodel.data.observe(this) {
            adapter.updateData(it!!)
            Log.i("data", it.toString())
        }
    }
    private fun inti() {
        repository = Repository(RetrofitInstance.getInstance())
        datamodelfactory = DataViewmodelProviderFactory(repository)
        dataViewmodel = ViewModelProvider(this, datamodelfactory).get(DataViewModel::class.java)
        recyclerView = findViewById(R.id.RV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = VridAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(dataViewmodel.scrollListener)
    }
}