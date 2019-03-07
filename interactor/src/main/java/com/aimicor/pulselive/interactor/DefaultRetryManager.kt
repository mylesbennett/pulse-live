package com.aimicor.pulselive.interactor

import io.reactivex.Flowable
import org.reactivestreams.Publisher

internal class DefaultRetryManager : RetryManager {
    override fun retries(errorFlowable: Flowable<Throwable>): Publisher<RetryEvent> {
        return errorFlowable.flatMap { throw(it) }
    }
}