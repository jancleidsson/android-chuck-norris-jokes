package com.jss.chucknorrisjokes.core.usecase

import com.jss.chucknorrisjokes.core.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(private val categoryRepository: CategoryRepository) {
    operator fun invoke() = categoryRepository.getAll()
}