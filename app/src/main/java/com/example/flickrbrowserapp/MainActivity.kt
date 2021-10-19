package com.example.flickrbrowserapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val apiInterface = ApiClint().getClient()?.create(APIInterface::class.java)
    var tags=""
    val listphotos=ArrayList<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch.setOnClickListener {
               tags=edSearch.text.toString()
               fetchData(tags)
           }

    }

    fun fetchData(tags:String){
        if (apiInterface!=null){
            apiInterface.search(tags)?.enqueue(object : Callback<Flickr?> {
                override fun onResponse(call: Call<Flickr?>, response: Response<Flickr?>) {
                    val photos=response.body()!!.photos.photo
                    for (photo in photos){
                     listphotos.add(photo)
                    }
                    recyclerView.adapter=RVAdapter(listphotos,this@MainActivity)
                    recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

                }
                override fun onFailure(call: Call<Flickr?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();

                }
            })
        }
    }

}