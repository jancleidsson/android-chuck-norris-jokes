package com.jss.chucknorrisjokes.framework

import com.jss.chucknorrisjokes.core.usecase.*
import javax.inject.Inject

class UseCases @Inject constructor(
        val getCategories: GetCategories,
        val getRandomJoke: GetRandomJoke,
        val addFavoriteJoke: AddFavoriteJoke,
        val removeFavoriteJoke: RemoveFavoriteJoke,
        val getFavoriteJoke: GetFavoriteJoke
        ) {
}
