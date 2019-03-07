package com.aimicor.pulselive

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class InteractorSchedulers {

    fun io(): Scheduler = Schedulers.io()
}