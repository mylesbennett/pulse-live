package com.aimicor.pulselive.view

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun Fragment.navigate(@IdRes resId: Int, args: Bundle?) {
    NavHostFragment.findNavController(this).navigate(resId, args)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T) = T::class.java.let {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T) = T::class.java.let {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}

fun <T> unsynchronizedLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

fun <T> Observable<T>.observe(lifecycle: Lifecycle, onNext: (it: T) -> Unit) {
    LiveObserver(lifecycle, subscribe(onNext))
}

private class LiveObserver(
    private val lifecycle: Lifecycle,
    private val disposable: Disposable
) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        disposable.dispose()
        lifecycle.removeObserver(this)
    }
}