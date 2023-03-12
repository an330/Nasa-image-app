package com.example.nasaapi

import android.content.Context
import android.content.Intent


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso


//lateinit var data_list:Mydata
//private val context: ArrayList<Mydata>,


class MyAdapter( private val context: Context ):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var data_list = mutableListOf<PictureInfo> ()
    fun setMyData(data: List<PictureInfo>) {
        this.data_list = data.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(Itemview:View):RecyclerView.ViewHolder(Itemview){


        val tv_title:TextView=itemView.findViewById(R.id.tv_title)
        //val tv_date:TextView=itemView.findViewById(R.id.tv_date)
        //val tv_explanation:TextView=itemView.findViewById(R.id.tv_explanation)
        val imagev:ImageView=itemView.findViewById(R.id.imview)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val currentItem = data_list[position]
            //holder.tv_date.text= currentItem.date
            Log.e("MyAdapter", "date coming")
           val titles= listOf(currentItem.title)

            holder.tv_title.text = currentItem.title
            //holder.tv_explanation.text= currentItem.explanation


            Picasso.get()
                .load(currentItem.url)


                .into(holder.imagev)
            Log.e("IMage", "image coming")
            holder.itemView.setOnClickListener(View.OnClickListener {

                val i = Intent(context, DetailActivity::class.java)
                i.putExtra("date", currentItem.date)

                i.putExtra("explanation", currentItem.explanation)
                i.putExtra("url",currentItem.url)
                context.startActivity(i)

            })


        }





    override fun getItemCount(): Int {
        return data_list.size
        print("${data_list.size}")
    }


}
