package com.aimicor.pulselive.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ViewModelSchedulers @Inject constructor() {

    fun mainThread() = AndroidSchedulers.mainThread()
}