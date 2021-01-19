package com.project.wisataapps

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.wisataapps.Adapter.WisataAdapter
import com.project.wisataapps.Model.ResponseServer
import com.project.wisataapps.Model.Wisata
import com.project.wisataapps.Network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progress = findViewById<ProgressBar>(R.id.progress)

        if (isConnect()) {
            ConfigNetwork.getRetrofit().getDataWisata().enqueue(object : Callback<ResponseServer> {
                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("response server", response.message())

                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        val status = response.body()?.status_code
                        if (status == 200) {
                            val data = response.body()?.data
                            showData(data)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    t.message?.let { Log.d("error server", it) }
                    progress.visibility = View.GONE
                }
            })
        }else{
            progress.visibility = View.GONE
            Toast.makeText(this,"No Internet Access", Toast.LENGTH_LONG).show()
        }
    }

    private fun showData(data: ArrayList<Wisata>?) {
        val list_wisata = findViewById<RecyclerView>(R.id.list_wisata)
        list_wisata.adapter = WisataAdapter(data)
    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager  = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo !== null && connect.activeNetworkInfo!!.isConnected
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}