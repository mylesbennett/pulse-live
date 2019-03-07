package com.aimicor.pulselive.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aimicor.pulselive.ItemDetails
import com.aimicor.pulselive.interactor.Details
import com.aimicor.pulselive.viewmodel.ViewModelSchedulers
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var details: Details

    @Mock
    private lateinit var schedulers: ViewModelSchedulers

    private val observer = TestObserver<DetailViewModel>()

    @Before
    fun `set up`() {
        `when`(schedulers.mainThread()).thenReturn(Schedulers.trampoline())
    }

    companion object {
        private val postDetails = ItemDetails("title", "body", "user", "comments")
    }

    @Test
    fun `can get details`() {
        `when`(details.fetch(anyInt())).thenReturn(Single.just(postDetails))

        val detailViewModel = DetailViewModel(1, details, schedulers)
        detailViewModel.detailViewModelEvent.subscribe(observer)

        observer.assertValue(detailViewModel)
        assertEquals(postDetails, detailViewModel.itemDetails)
    }
}