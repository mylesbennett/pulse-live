package com.aimicor.pulselive.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Item(
    @SerializedName("date") val date: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("title") val title: String
)