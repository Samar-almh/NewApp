package com.samar.newapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import api.NewApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "NewApp"

class NewApp {
    private val newApi: NewApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.191.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newApi = retrofit.create(NewApi::class.java)
    }

    fun fetchNews(): LiveData<List<NewItem>> {
        val responseLiveData: MutableLiveData<List<NewItem>> = MutableLiveData()
        val newRequest: Call<List<NewItem>> = newApi.fetchNews()
        newRequest.enqueue(object : Callback<List<NewItem>> {
            override fun onFailure(call: Call<List<NewItem>>, t: Throwable) {
                Log.e(TAG, "Failed to fetch news", t)
            }
            override fun onResponse(
                call: Call<List<NewItem>>,
                response: Response<List<NewItem>>
            ) {
                Log.d(TAG, "Response received")
                val appResponse: List<NewItem>? = response.body()
               // val newResponse: NewResponse? = appResponse?.news
                var newItems: List<NewItem> = appResponse
                    ?: mutableListOf()

                responseLiveData.value = newItems
            }
        })
        return responseLiveData
    }
}
