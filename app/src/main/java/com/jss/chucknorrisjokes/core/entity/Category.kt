package com.jss.chucknorrisjokes.core.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var name: String
): Parcelable