package com.aimicor.pulselive.viewmodel.parent

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import javax.inject.Inject

@ParentScope
class ParentViewModel @Inject constructor(): ViewModel() {

    val showProgress = ObservableInt(View.VISIBLE)

    internal fun showProgress(show: Boolean) {
        if (show) showProgress.set(View.VISIBLE)
        else showProgress.set(View.GONE)
    }
}