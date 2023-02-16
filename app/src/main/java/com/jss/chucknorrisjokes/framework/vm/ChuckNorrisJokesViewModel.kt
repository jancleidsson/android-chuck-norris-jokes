package com.jss.chucknorrisjokes.framework.vm

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.core.entity.Joke
import com.jss.chucknorrisjokes.framework.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChuckNorrisJokesViewModel @Inject constructor(
    application: Application,
    private val useCases: UseCases,
) : AndroidViewModel(application) {
    val categoriesList = MutableLiveData<List<Category>?>()
    val randomJoke = MutableLiveData<Joke?>()
    val isFavoriteJoke = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var compositeDisposable = CompositeDisposable()

    fun getCategories() {
        loading.postValue(true)
        val disposableCategory = useCases.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categoriesList.postValue(it)
            }, {
                error.postValue(it)
                loading.postValue(false)
            }, {
                loading.postValue(false)
            })
        compositeDisposable.add(disposableCategory)
    }

    fun getRandomJoke(category: Category) {
        loading.postValue(true)
        val disposableRandomJoke = useCases.getRandomJoke(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                randomJoke.postValue(it)
            }, {
                error.postValue(it)
                loading.postValue(false)
            }, {
                loading.postValue(false)
            })
        compositeDisposable.add(disposableRandomJoke)
    }

    fun addFavoriteJoke(joke: Joke) {
        viewModelScope.launch {
            useCases.addFavoriteJoke(joke)
        }
    }

    fun removeFavoriteJoke(joke: Joke) {
        viewModelScope.launch {
            useCases.removeFavoriteJoke(joke)
        }
    }

    fun isFavoriteJoke(joke: Joke) {
        loading.postValue(true)
        viewModelScope.launch {
            if (useCases.getFavoriteJoke(joke) != null) {
                isFavoriteJoke.postValue(true)
            } else {
                isFavoriteJoke.postValue(false)
            }
        }.invokeOnCompletion {
            loading.postValue(false)
        }
    }

    fun clear() {
        categoriesList.value = null
        randomJoke.value = null
        compositeDisposable.clear()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    @Override
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}