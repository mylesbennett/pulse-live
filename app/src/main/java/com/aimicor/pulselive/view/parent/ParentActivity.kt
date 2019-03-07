package com.aimicor.pulselive.view.parent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.aimicor.pulselive.view.R
import com.aimicor.pulselive.view.databinding.MainActivityBinding
import com.aimicor.pulselive.view.di.injector
import com.aimicor.pulselive.view.getViewModel
import com.aimicor.pulselive.view.unsynchronizedLazy
import kotlinx.android.synthetic.main.main_activity.*

class ParentActivity : AppCompatActivity() {

    private val viewModel by unsynchronizedLazy { getViewModel { injector.parentComponent.parentViewModel} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.parentComponent.inject(this)
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity).viewModel = viewModel
        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }
}