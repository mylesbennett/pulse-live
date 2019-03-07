package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ApiService
import com.aimicor.pulselive.InteractorSchedulers

abstract class ApiServiceInteractor() {

    private companion object {
        private val _apiService = ApiService.create()
        private val _schedulers = InteractorSchedulers()
    }

    internal val apiService = _apiService
    internal val schedulers = _schedulers
}
