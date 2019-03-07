package com.aimicor.pulselive.model

import com.google.gson.annotations.SerializedName

data class ItemDetailContainer(
    @SerializedName("item") val item: ItemDetail
)