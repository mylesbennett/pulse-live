package com.aimicor.pulselive.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.aimicor.pulselive.view.*
import com.aimicor.pulselive.view.databinding.MainFragmentBinding
import com.aimicor.pulselive.view.detail.DetailFragment
import com.aimicor.pulselive.view.di.injector
import com.aimicor.pulselive.viewmodel.inflateBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val viewModel by unsynchronizedLazy { getViewModel { injector.parentComponent.mainViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.listItemClickEvent.observe(this.lifecycle) {
            navigate(R.id.action_mainFragment_to_detailFragment, DetailFragment.with(it))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = inflater.inflateBinding<MainFragmentBinding>(R.layout.main_fragment, container)
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvData.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}
