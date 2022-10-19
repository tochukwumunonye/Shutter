package com.example.shutter.shared

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shutter.R
import com.example.shutter.data.entity.Data
import com.example.shutter.databinding.ItemSearchBinding

class SearchViewHolder(
    private val binding: ItemSearchBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(data: Data) {
        binding.apply {

            Glide.with(itemView)
                .load(data.assets.preview.url)
                .error(R.drawable.image_placeholder)
                .into(albumImage)
        }
    }
}
