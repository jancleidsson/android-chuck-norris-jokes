package com.jss.chucknorrisjokes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jss.chucknorrisjokes.R
import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.databinding.FragmentJokesCategoryListBinding
import com.jss.chucknorrisjokes.framework.vm.ChuckNorrisJokesViewModel
import com.jss.chucknorrisjokes.presentation.adapter.JokesCategoryAdapter

class JokesCategoryFragment : Fragment() {

    private val mBinding by lazy {
        FragmentJokesCategoryListBinding.inflate(layoutInflater)
    }

    private val mCategoryAdapter by lazy {
        JokesCategoryAdapter { categoryClicked ->
            goToRandomJoke(categoryClicked)
        }
    }

    private val mChuckNorrisJokesViewModel: ChuckNorrisJokesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observerViewModel()
        mBinding.retryButton.setOnClickListener {
            mChuckNorrisJokesViewModel.getCategories()
            mBinding.retryButton.visibility = View.INVISIBLE
        }
        mChuckNorrisJokesViewModel.getCategories()
    }

    override fun onStop() {
        super.onStop()
        mChuckNorrisJokesViewModel.clear()
    }

    private fun initAdapter() {
        mBinding.categoryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mCategoryAdapter
        }
    }

    private fun observerViewModel() {
        mChuckNorrisJokesViewModel.categoriesList.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {
                mBinding.retryButton.visibility = View.INVISIBLE
                mCategoryAdapter.updateCategories(categories)
            }
        }

        mChuckNorrisJokesViewModel.loading.observe(viewLifecycleOwner) { loading ->
            mBinding.loading.isVisible = loading
            mBinding.categoryList.isVisible = loading.not()
        }

        mChuckNorrisJokesViewModel.error.observe(viewLifecycleOwner) { error ->
            mBinding.retryButton.visibility = View.VISIBLE
            Toast.makeText(
                context,
                getString(R.string.error).plus(error.message),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun goToRandomJoke(category: Category) {
        val action: NavDirections =
            JokesCategoryFragmentDirections.actionCategoryFragmentToRandomJokeFragment(category)
        Navigation.findNavController(mBinding.categoryList).navigate(action)
    }
}