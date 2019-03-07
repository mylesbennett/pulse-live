package com.aimicor.pulselive.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ItemDetail(
    @SerializedName("body") val body: String,
    @SerializedName("date") val date: Date,
    @SerializedName("id") val id: Int,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("title") val title: String
)