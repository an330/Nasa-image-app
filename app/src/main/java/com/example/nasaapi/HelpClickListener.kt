package com.example.nasaapi

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class HelpClickListener:View.OnClickListener {
    var first = 0

    override fun onClick(v: View?) {
        //val view=v?.rootView


        when(v?.id){

            R.id.button1->
            {

                val tottal = recycleadapter.getItemCount()
                first += 1
                if(first>tottal){
                    first=0
                }

                recycle.smoothScrollToPosition(first)

                println("tottal=$tottal")
                println("first=$first")

                  //Toast.makeText(v.context,"button next",Toast.LENGTH_LONG).show()
            }
            R.id.button2->
            {

                first-=1
                if (first<=0){
                    first=0
                }
                recycle.smoothScrollToPosition(first)




            }
        }
    }
}