package com.aimicor.pulselive.interactor

import io.reactivex.Flowable
import org.reactivestreams.Publisher

interface RetryManager {
    fun retries(errorFlowable: Flowable<Throwable>): Publisher<RetryEvent>
}