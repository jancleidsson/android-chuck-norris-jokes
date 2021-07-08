package com.jss.chucknorrisjokes.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jss.chucknorrisjokes.R
import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.databinding.FragmentRandomJokeBinding
import com.jss.chucknorrisjokes.framework.vm.ChuckNorrisJokesViewModel
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.TouchableMovementMethod
import com.klinker.android.link_builder.applyLinks


class RandomJokeFragment : Fragment() {

    private val args: RandomJokeFragmentArgs by navArgs()

    private val mBinding by lazy {
        FragmentRandomJokeBinding.inflate(layoutInflater)
    }

    private val mChuckNorrisJokesViewModel: ChuckNorrisJokesViewModel by activityViewModels()
    private var currentJoke: Joke? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRandomJokeFromCategory(args.category)
        observerViewModel()
        mBinding.retryButton.setOnClickListener { getRandomJokeFromCategory(args.category) }
        mBinding.jokeFavorite.setOnCheckedChangeListener { _, isChecked ->
            currentJoke?.let {
                if (isChecked) {
                    mChuckNorrisJokesViewModel.addFavoriteJoke(it)
                } else {
                    mChuckNorrisJokesViewModel.removeFavoriteJoke(it)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mChuckNorrisJokesViewModel.clear()
    }

    private fun getRandomJokeFromCategory(category: Category) {
        mChuckNorrisJokesViewModel.getRandomJoke(category)
    }

    private fun observerViewModel() {
        val currentJokeCategory = getString(R.string.category).plus(": ").plus(args.category.name)
        mChuckNorrisJokesViewModel.randomJoke.observe(viewLifecycleOwner, { joke ->
            if (joke != null) {
                mBinding.retryButton.visibility = View.INVISIBLE
                setupJokeHyperlink(joke.url, currentJokeCategory)
                mBinding.jokeValue.text = joke.value
                Glide.with(this).load(joke.icon).placeholder(R.drawable.ic_placeholder_image).into(mBinding.jokeImage)
                checkFavoriteJoke(joke)
                currentJoke = joke
            }
        })

        mChuckNorrisJokesViewModel.loading.observe(viewLifecycleOwner, { loading ->
            mBinding.loading.isVisible = loading
            mBinding.jokeLink.isVisible = loading.not()
            mBinding.jokeImage.isVisible = loading.not()
            mBinding.jokeValue.isVisible = loading.not()
            mBinding.jokeFavorite.isVisible = loading.not()
        })

        mChuckNorrisJokesViewModel.error.observe(viewLifecycleOwner, { error ->
            mBinding.retryButton.visibility = View.VISIBLE
            Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
        })

        mChuckNorrisJokesViewModel.isFavoriteJoke.observe(viewLifecycleOwner, { isFavoriteJoke ->
                mBinding.jokeFavorite.isChecked = isFavoriteJoke
        })
    }

    private fun checkFavoriteJoke(joke: Joke) {
        mChuckNorrisJokesViewModel.isFavoriteJoke(joke)
    }

    private fun setupJokeHyperlink(url: String, text: String) {
        mBinding.jokeLink.text = text
        val link: Link =
            Link(text).setBold(true).setTextColor(R.color.purple_700).setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        mBinding.jokeLink.applyLinks(link)
        mBinding.jokeLink.movementMethod = TouchableMovementMethod()
    }
}