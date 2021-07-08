package com.jss.chucknorrisjokes.core.entity

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class CategoryTest {

    @Test
    fun validateCategoryScheme() {
        val category = Category("teste")
        assertThat(category.name, Matchers.isA(String::class.java))
    }
}