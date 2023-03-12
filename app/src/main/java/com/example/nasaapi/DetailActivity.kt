package com.example.nasaapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val date=intent.getStringExtra("date")
        val title=intent.getStringExtra("title")
        val explanation=intent.getStringExtra("explanation")
        val url=intent.getStringExtra("url")

        val tv_date: TextView =findViewById<TextView?>(R.id.tv_date)
        val tv_explanation: TextView =findViewById(R.id.tv_explanation)
        val image:ImageView=findViewById(R.id.imview1)
        Picasso.get()
            .load(url)


            .into(image)
        tv_date.setText(date)
        tv_explanation.setText(explanation)

    }
}