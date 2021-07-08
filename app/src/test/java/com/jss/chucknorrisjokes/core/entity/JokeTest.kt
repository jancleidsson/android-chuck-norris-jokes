package com.jss.chucknorrisjokes.core.entity

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class JokeTest {

    @Test
    fun validateJokeScheme() {
        val joke = Joke("icon_url", "abc", "url", "joke")
        assertThat(joke.icon, Matchers.isA(String::class.java))
        assertThat(joke.id, Matchers.isA(String::class.java))
        assertThat(joke.url, Matchers.isA(String::class.java))
        assertThat(joke.value, Matchers.isA(String::class.java))
    }
}