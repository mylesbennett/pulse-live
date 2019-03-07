package com.aimicor.pulselive.viewmodel.detail

import javax.inject.Inject

class DetailViewModelFactory @Inject constructor() {
    internal fun create(postId: Int) = DetailViewModel(postId)
}