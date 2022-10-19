package com.example.shutter.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shutter.constants.STARTING_PAGE
import com.example.shutter.data.entity.Data
import com.example.shutter.data.remote.api.APIService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShutterPagingSource @Inject constructor(
    private val query: String,
    private val service: APIService
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val page = params.key ?: STARTING_PAGE

        return try {
          val response = service.search(query, page)

            val data = response.data

            LoadResult.Page(
                data = data,
                prevKey = if(page == STARTING_PAGE) null else page -1,
                nextKey =if(data.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception : HttpException) {
            LoadResult.Error(exception)
        }
    }
}

