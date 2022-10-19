package com.example.shutter.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shutter.R
import com.example.shutter.databinding.LoadStateFooterBinding

class ImageLoadStateAdapter(private val retry: () -> Unit) :
LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding) :
            RecyclerView.ViewHolder(binding.root) {

                init {
                    binding.buttonRetry.setOnClickListener {
                        retry()
                    }
                }


        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState is LoadState.Error
                textViewError.isVisible = loadState is LoadState.Error

                if(loadState is LoadState.Error) {
                    textViewError.text = loadState.error.localizedMessage
                        ?: binding.root.context.getString(R.string.unknown_error_occurred)
                }

            }
        }
            }

}
