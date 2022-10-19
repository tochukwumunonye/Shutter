package com.example.shutter.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.PagingData
import com.example.shutter.data.entity.Data
import com.example.shutter.domain.ShutterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: SearchViewModel
    private val repository: ShutterRepository = mock()

    private val _res : MutableLiveData<PagingData<Data>> = MutableLiveData()
    val res : LiveData<PagingData<Data>> = _res

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `livedata should not be empty when repository returns paging state`() {
        runTest {
            val expected = getLiveData()
            whenever(repository.searchShutter("run")).thenReturn(res)
            viewModel = SearchViewModel(repository)
            viewModel.searchImages("run")
            val state = viewModel.photos
            assertNotNull(state)

        }
    }

    private fun getLiveData() : LiveData<PagingData<Data>>  = liveData{
        emit(PagingData.empty())
    }
}
