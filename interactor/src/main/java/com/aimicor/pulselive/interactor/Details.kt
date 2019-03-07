package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ApiService.Companion.dateFormat
import com.aimicor.pulselive.ItemDetails
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class Details(val retryManager: RetryManager = DefaultRetryManager()) : ApiServiceInteractor() {

    fun fetch(id: Int): Single<ItemDetails> =
        apiService.getItem(id)
            .retryWhen { retryManager.retries(it) }
            .subscribeOn(schedulers.io())
            .map { itemContainer -> itemContainer.item }
            .flatMap {
                Single.just(
                    ItemDetails(
                        it.title,
                        it.subtitle,
                        it.body,
                        SimpleDateFormat(dateFormat, Locale.UK).format(it.date)
                    )
                )
            }
}