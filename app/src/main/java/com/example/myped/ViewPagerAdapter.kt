package com.example.myped

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class ViewPagerAdapter(private val drawables: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

     inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val imageView: ImageView = itemView.findViewById(R.id.obmage)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.onboarding_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        drawables[position].let {
            holder.imageView.setImageResource(it)
        }
    }

    override fun getItemCount(): Int {
        return drawables.size
    }

}
