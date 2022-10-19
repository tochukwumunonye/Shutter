package com.example.shutter.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.shutter.data.entity.Data
import kotlinx.coroutines.flow.Flow

interface ShutterRepository {

     fun searchShutter(query: String): LiveData<PagingData<Data>>
}
