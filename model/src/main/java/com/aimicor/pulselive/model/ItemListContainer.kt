package com.aimicor.pulselive.model

import com.google.gson.annotations.SerializedName

data class ItemListContainer(
    @SerializedName("items") val items: List<Item>
)