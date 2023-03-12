package com.example.nasaapi

import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Myviewmodel:ViewModel() {
    var userData:MutableLiveData<List<PictureInfo>>
    val _userdata: LiveData<List<PictureInfo>>
        get() =userData

    init {
        userData=MutableLiveData()
    }
    fun getData():MutableLiveData<List<PictureInfo>>{
        return userData
    }
    fun getUserdata(api_key:String){
        val myapi=api.retrofitService.getUserdata(api_key,"5")


        myapi.enqueue(object :Callback<List<PictureInfo>>{
            override fun onFailure(call: Call<List<PictureInfo>>,t:Throwable){
                Log.e("TAG_TAG","Failed :"+t.message)

            }


            override fun onResponse(call: Call<List<PictureInfo>>, response: Response<List<PictureInfo>>) {
                userData.value=response.body()
                Log.d("Myviewmodel","Response coming")


            }
        })

    }
}