package com.example.vridapp.mvvm.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vridapp.mvvm.model.dataresponse.VridResponseItem
import com.example.vridapp.R
import com.squareup.picasso.Picasso

class VridAdapter( private var items : List<VridResponseItem>) : RecyclerView.Adapter<VridAdapter.VridViewHolder>() {
    class VridViewHolder( view : View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.TV)
        val image : ImageView = view.findViewById(R.id.IV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent, false)
        return VridViewHolder(view)
    }

    override fun onBindViewHolder(holder: VridViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title.rendered
        if ( item.jetpack_featured_media_url != null ){
            Picasso.get().load(item.jetpack_featured_media_url).into(holder.image)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WebViewActivity::class.java)
            intent.putExtra("URL", item.link)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newItems: List<VridResponseItem>) {
        items = newItems
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

}