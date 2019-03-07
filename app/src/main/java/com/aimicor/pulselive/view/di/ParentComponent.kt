package com.aimicor.pulselive.view.di

import com.aimicor.pulselive.view.parent.ParentActivity
import com.aimicor.pulselive.viewmodel.main.MainViewModel
import com.aimicor.pulselive.viewmodel.parent.ParentScope
import com.aimicor.pulselive.viewmodel.parent.ParentViewModel
import dagger.Subcomponent

@ParentScope
@Subcomponent
interface ParentComponent {
    fun inject(parentActivity: ParentActivity)

    val parentViewModel: ParentViewModel
    val mainViewModel: MainViewModel
}