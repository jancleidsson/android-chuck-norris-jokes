package com.jss.chucknorrisjokes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jss.chucknorrisjokes.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChuckNorrisJokesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chuck_noris_jokes)
    }
}