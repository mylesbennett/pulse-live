package com.aimicor.pulselive.view.di

import android.app.Application
import com.aimicor.pulselive.view.unsynchronizedLazy

class ViewApplication: Application(), DaggerComponentProvider {

    override val applicationComponent: ApplicationComponent by unsynchronizedLazy {
        DaggerApplicationComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override val parentComponent: ParentComponent by unsynchronizedLazy {
        applicationComponent.parentComponent()
    }
}