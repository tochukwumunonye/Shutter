package com.example.shutter.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shutter.R
import com.example.shutter.databinding.FragmentSearchBinding
import com.example.shutter.shared.SearchListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    var imageAdapter = SearchListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)


        binding.apply {
            searchRecyclerView.apply {
                adapter = imageAdapter.withLoadStateFooter(
                    ImageLoadStateAdapter(imageAdapter::retry)
                )
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }
        }

        viewModel.photos.observe(viewLifecycleOwner){
            imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        binding.searchButton.setOnClickListener {
            val text = binding.editText.text.toString()
            viewModel.searchImages(text)
        }

        imageAdapter.addLoadStateListener { loadState ->
            binding.apply {
                progressSearch.isVisible = loadState.source.refresh is LoadState.Loading
                searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                if(loadState.source.refresh is LoadState.Error) {
                    Toast.makeText(
                        requireContext(),
                        R.string.results_could_not_be_loaded ,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    imageAdapter.itemCount < 1) {
                    searchRecyclerView.isVisible = false
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}