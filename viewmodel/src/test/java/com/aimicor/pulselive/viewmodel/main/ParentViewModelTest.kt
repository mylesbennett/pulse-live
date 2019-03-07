package com.aimicor.pulselive.viewmodel.main

import android.view.View
import com.aimicor.pulselive.viewmodel.parent.ParentViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test

class ParentViewModelTest {

    private val parentViewModel = ParentViewModel()

    @Test
    fun `can hide progress bar`() {
        parentViewModel.showProgress(false)
        assertEquals(View.GONE, parentViewModel.showProgress.get())
    }

    @Test
    fun `can show progress bar`() {
        parentViewModel.showProgress(true)
        assertEquals(View.VISIBLE, parentViewModel.showProgress.get())
    }
}