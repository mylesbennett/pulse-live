package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ApiService.Companion.dateFormat
import com.aimicor.pulselive.model.Item
import com.aimicor.pulselive.model.ItemDetail
import com.aimicor.pulselive.model.ItemDetailContainer
import com.aimicor.pulselive.model.ItemListContainer
import java.text.SimpleDateFormat

val dateString = "18/04/2013 11:48"
val date = SimpleDateFormat(dateFormat).parse(dateString)

val itemList = ItemListContainer(listOf(
    Item(date, 34, "subtitle1", "title1"),
    Item(date, 78, "subtitle2", "title2"))
)

val item = ItemListContainer(listOf(Item(date, 23, "subtitle3", "title3")))

val itemContainer = ItemDetailContainer(ItemDetail("body1", date, 90, "subtitle4", "title4"))