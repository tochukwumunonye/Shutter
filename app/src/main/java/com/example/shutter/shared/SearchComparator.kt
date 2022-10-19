package com.example.shutter.shared

import androidx.recyclerview.widget.DiffUtil
import com.example.shutter.data.entity.Data

class SearchComparator : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
       return oldItem.aspect == newItem.aspect
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.description == newItem.description
    }
}
