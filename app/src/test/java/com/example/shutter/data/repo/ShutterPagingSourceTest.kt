package com.example.shutter.data.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.shutter.data.entity.*
import com.example.shutter.data.remote.api.APIService
import com.example.shutter.data.repository.ShutterPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ShutterPagingSourceTest {

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: APIService

    private lateinit var shutterPagingSource: ShutterPagingSource

    companion object {
        val apiResponse = Pics(
            data = listOf(
                Data(
                    1.1, Assets(
                        Preview(1,"kk", 2)
                    ),
                    "jjj"
                ),
                Data(
                    1.1, Assets(
                        Preview(1,"kk", 2)
                    ),
                    "jjj"
                )
            ),
            1,2,"run", SpellcheckInfo(), 2
        )
    }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        shutterPagingSource = ShutterPagingSource("run", api)
    }

    @Test
    fun `happy`() = runBlocking {
        BDDMockito.given(api.search("run", 1)).willReturn(apiResponse)
        val expected = PagingSource.LoadResult.Page(
            data = apiResponse.data,
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expected, shutterPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
