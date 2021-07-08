package com.jss.chucknorrisjokes.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "jokes")
data class Joke(
    @SerializedName("icon_url")
    var icon: String,
    @PrimaryKey
    @SerializedName("id")
    var id: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("value")
    var value: String
)