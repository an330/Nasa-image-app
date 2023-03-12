package com.example.nasaapi

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.postDelayed
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext

lateinit var recycleadapter:MyAdapter
private lateinit var button1: Button
private lateinit var button2:Button
lateinit var recycle: RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.recyclerview)

        recycleadapter = MyAdapter(this)

        var Myviewmode = ViewModelProviders.of(this).get(Myviewmodel::class.java)

        Myviewmode.getData().observe(this, Observer<List<PictureInfo>> {
            if (it != null) {

                recycleadapter.setMyData(it)
                Log.d("MainActivity", "api data succesful")
            } else {
                Toast.makeText(this@MainActivity, "error from api", Toast.LENGTH_LONG).show()
            }
        })
        Myviewmode.getUserdata("U5Rmst29V8PcfXhk1lgdft4y32mG9UxWeEi5XV4i")
        Log.d("MainActivity", "getUserdata successful")

        recycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycle.adapter = recycleadapter

       button1=findViewById(R.id.button1)
        button2=findViewById(R.id.button2)
        val helpClickListener=HelpClickListener()
        button1.setOnClickListener(helpClickListener)
        button2.setOnClickListener(helpClickListener)


        /*var first = 0

        button1.setOnClickListener {

            val tottal = recycleadapter.getItemCount()

                recycle.smoothScrollToPosition(first)

                first += 1
            if(first>=tottal){
                first=0
            }
        }*/






    }
    interface Getrecycle{
        fun onrecycle(recycle:String)
    }

}




