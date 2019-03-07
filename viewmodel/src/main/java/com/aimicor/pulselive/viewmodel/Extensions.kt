package com.aimicor.pulselive.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> LayoutInflater.inflateBinding(@LayoutRes layoutId: Int, container: ViewGroup?): T =
    DataBindingUtil.inflate(this, layoutId, container, false)
