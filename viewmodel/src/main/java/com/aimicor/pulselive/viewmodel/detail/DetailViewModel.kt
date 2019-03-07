package com.aimicor.pulselive.viewmodel.detail

import com.aimicor.pulselive.ItemDetails
import com.aimicor.pulselive.interactor.Details
import com.aimicor.pulselive.viewmodel.ViewModelSchedulers
import io.reactivex.Observable
import java.io.Serializable

// Conceptually a view-model but no need to be a ViewModel derivative
class DetailViewModel internal constructor(
    postId: Int,
    details: Details = Details(),
    schedulers: ViewModelSchedulers = ViewModelSchedulers()
) : Serializable {

    lateinit var itemDetails: ItemDetails
        private set

    val detailViewModelEvent = details.fetch(postId)
        .observeOn(schedulers.mainThread())
        .doOnSuccess { itemDetails = it }
        .toObservable()
        .flatMap { Observable.just(this) }
}
