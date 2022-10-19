package com.example.shutter.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.shutter.ui.search.SearchFragment
import javax.inject.Inject

class TestFragmentFactory @Inject constructor(
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            SearchFragment::class.java.name -> SearchFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}

