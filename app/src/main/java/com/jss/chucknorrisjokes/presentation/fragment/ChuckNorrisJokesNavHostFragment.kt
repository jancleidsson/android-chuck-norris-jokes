package com.jss.chucknorrisjokes.presentation.fragment

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.jss.chucknorrisjokes.presentation.fragment.factory.ChuckNorrisJokesFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChuckNorrisJokesNavHostFragment: NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: ChuckNorrisJokesFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}