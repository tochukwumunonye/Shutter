package com.example.shutter.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.shutter.constants.NETWORK_PAGE_SIZE
import com.example.shutter.data.entity.Data
import com.example.shutter.data.remote.api.APIService
import com.example.shutter.data.repository.ShutterPagingSource
import com.example.shutter.domain.ShutterRepository
import javax.inject.Inject

class ShutterRepositoryImplementation @Inject constructor(
    private val api: APIService
) : ShutterRepository{

    override fun searchShutter(query: String): LiveData<PagingData<Data>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ShutterPagingSource(query, api) }

        ).liveData

    }
}
