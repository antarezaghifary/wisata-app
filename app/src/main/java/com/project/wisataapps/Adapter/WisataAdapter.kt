package com.project.wisataapps.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.wisataapps.Detail.DetailWisataActivity
import com.project.wisataapps.Model.Wisata
import com.project.wisataapps.R

class WisataAdapter(private val data: ArrayList<Wisata>?) : RecyclerView.Adapter<WisataAdapter.WisataHolder>() {

    class WisataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.gambar)
        val textNama = itemView.findViewById<TextView>(R.id.namaLokasi)
        val textTempat = itemView.findViewById<TextView>(R.id.namaTempat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata,parent,false)
        val holder = WisataHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: WisataHolder, position: Int) {
        holder.textTempat.text = data?.get(position)?.nama_tempat
        holder.textNama.text = data?.get(position)?.lokasi
        Glide.with(holder.itemView.context).load(data?.get(position)?.gambar).into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailWisataActivity::class.java)
            intent.putExtra("jdl", data?.get(position)?.nama_tempat)
            intent.putExtra("img", data?.get(position)?.gambar)
            intent.putExtra("desc", data?.get(position)?.deskripsi)
            holder.itemView.context.startActivity(intent)
        }
    }
}