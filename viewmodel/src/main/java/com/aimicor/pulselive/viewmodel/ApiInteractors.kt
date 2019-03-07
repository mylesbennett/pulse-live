package com.aimicor.pulselive.viewmodel

import com.aimicor.pulselive.interactor.Details
import com.aimicor.pulselive.interactor.Items
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInteractors @Inject constructor() {

    internal val items: Items by lazy { Items() }
    internal val details: Details by lazy { Details() }
}