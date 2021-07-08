package com.jss.chucknorrisjokes.presentation.fragment.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jss.chucknorrisjokes.presentation.fragment.JokesCategoryFragment
import com.jss.chucknorrisjokes.presentation.fragment.RandomJokeFragment
import javax.inject.Inject

class ChuckNorrisJokesFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            JokesCategoryFragment::class.java.name -> {
                JokesCategoryFragment()
            }
            RandomJokeFragment::class.java.name -> {
                RandomJokeFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}