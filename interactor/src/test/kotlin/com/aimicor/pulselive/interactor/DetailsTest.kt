package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ItemDetails
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.Mockito.`when`
import org.reactivestreams.Publisher

class DetailsTest : ApiServiceTest<Details>(Details::class.java) {

    private val result = TestObserver.create<ItemDetails>()

    @Test
    fun `expected info returned given non-null results`() {
        `when`(apiService.getItem(12345)).thenReturn(Single.just(itemContainer))
        val expected =
            ItemDetails(itemContainer.item.title, itemContainer.item.subtitle, itemContainer.item.body, dateString)

        subject().fetch(12345).subscribe(result)

        result.assertValue { it.equals(expected) }
    }

    @Test
    fun `can fail`() {
        val throwable = Throwable()
        `when`(apiService.getItem(12345)).thenReturn(Single.error(throwable))

        subject().fetch(12345).subscribe(result)

        result.assertError(throwable)
    }

    @Test
    fun `can manage fail`() {
        `when`(apiService.getItem(12345)).thenReturn(Single.error(Throwable()))
        val errorHandler = object : RetryManager {
            override fun retries(errorFlowable: Flowable<Throwable>): Publisher<RetryEvent> = Publisher { }
        }

        subject(errorHandler).fetch(12345).subscribe(result)

        result.assertNoErrors()
    }
}