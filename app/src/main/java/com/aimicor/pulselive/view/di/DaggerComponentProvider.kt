package com.aimicor.pulselive.view.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface DaggerComponentProvider {

  val applicationComponent: ApplicationComponent
  val parentComponent: ParentComponent
}

val Fragment.injector get() = (activity?.application as DaggerComponentProvider)
val FragmentActivity.injector get() = (application as DaggerComponentProvider)

