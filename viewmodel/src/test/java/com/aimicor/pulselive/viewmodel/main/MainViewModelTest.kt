package com.aimicor.pulselive.viewmodel.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aimicor.pulselive.ItemSummary
import com.aimicor.pulselive.interactor.Items
import com.aimicor.pulselive.viewmodel.ApiInteractors
import com.aimicor.pulselive.viewmodel.ViewModelSchedulers
import com.aimicor.pulselive.viewmodel.detail.DetailViewModel
import com.aimicor.pulselive.viewmodel.detail.DetailViewModelFactory
import com.aimicor.pulselive.viewmodel.parent.ParentViewModel
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainAdapter: MainAdapter

    @Mock
    private lateinit var schedulers: ViewModelSchedulers

    @Mock
    private lateinit var items: Items

    @Mock
    private lateinit var detailViewModelFactory: DetailViewModelFactory

    @Mock
    private lateinit var apiInteractors: ApiInteractors

    @Mock
    private lateinit var parentViewModel: ParentViewModel

    private val mainViewModel get() = MainViewModel(mainAdapter, apiInteractors, schedulers, detailViewModelFactory, parentViewModel)

    private val clickSubject = PublishSubject.create<Int>()
    private val listSubject = Single.create<List<ItemSummary>> {  }
    private val observer = TestObserver<DetailViewModel>()

    companion object {
        private val post1 = ItemSummary(1, "post1")
        val postList1 = listOf(post1)
    }

    @Before
    fun `set up`() {
        `when`(apiInteractors.items).thenReturn(items)
        `when`(schedulers.mainThread()).thenReturn(Schedulers.trampoline())
        `when`(mainAdapter.clickEvent).thenReturn(clickSubject)
        reset(parentViewModel)
    }

    @Test
    fun `can get a list`() {
        `when`(items.fetch()).thenReturn(Single.just(postList1))

        mainViewModel

        verify(mainAdapter).submitList(postList1)
        verify(parentViewModel).showProgress(false)
    }

    @Test
    fun `can click on item`() {
        val detailViewModel = mock(DetailViewModel::class.java)
        val detailsReceived = PublishSubject.create<DetailViewModel>()
        `when`(detailViewModel.detailViewModelEvent).thenReturn(detailsReceived)
        `when`(detailViewModelFactory.create(1)).thenReturn(detailViewModel)
        `when`(items.fetch()).thenReturn(listSubject)

        mainViewModel.listItemClickEvent.subscribe(observer)
        clickSubject.onNext(1)

        verify(parentViewModel).showProgress(true)

        detailsReceived.onNext(detailViewModel)
        observer.assertValue(detailViewModel)
        verify(parentViewModel).showProgress(false)
    }
}
