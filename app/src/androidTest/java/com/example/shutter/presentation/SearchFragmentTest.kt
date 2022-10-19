package com.example.shutter.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.shutter.data.entity.Assets
import com.example.shutter.data.entity.Data
import com.example.shutter.data.entity.Preview
import com.example.shutter.di.launchFragmentInHiltsContainer
import com.example.shutter.ui.search.SearchFragment
import com.example.shutter.R
import com.example.shutter.di.AndroidMainCoroutinesRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@RunWith(AndroidJUnit4ClassRunner::class)
class SearchFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var coroutinesRule = AndroidMainCoroutinesRule()

    private val data = listOf(
        Data(
            1.0,
            Assets(Preview(1,"http",2)),
            "image"
        ),
        Data(
            11.0,
            Assets(Preview(1,"https",2)),
            "image2"
        )
    )

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun isImageListVisible() {
        launchFragmentInHiltsContainer<SearchFragment>() {
            runTest {
                imageAdapter.submitData(PagingData.from(data))
            }
        }

       onView(withId(R.id.searchRecyclerView)).check(matches(isDisplayed()))

    }

}