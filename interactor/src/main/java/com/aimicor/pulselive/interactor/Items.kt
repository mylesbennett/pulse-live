package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ItemSummary
import io.reactivex.Single

class Items(val retryManager: RetryManager = DefaultRetryManager()) : ApiServiceInteractor() {

    fun fetch(): Single<List<ItemSummary>> =
        apiService.getItems()
            .retryWhen { retryManager.retries(it) }
            .subscribeOn(schedulers.io())
            .toObservable()
            .flatMapIterable { it.items }
            .map { item -> ItemSummary(item.id, item.title) }
            .toList()
}