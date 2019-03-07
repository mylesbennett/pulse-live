package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ItemSummary
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.Mockito.`when`
import org.reactivestreams.Publisher

class ItemsTest : ApiServiceTest<Items>(Items::class.java) {

    private val result = TestObserver.create<List<ItemSummary>>()

    @Test
    fun `can get post`() {
        `when`(apiService.getItems()).thenReturn(Single.just(item))
        subject().fetch().subscribe(result)
        result.assertValue(listOf(ItemSummary(item.items[0].id, item.items[0].title)))
    }

    @Test
    fun `can get post list`() {
        `when`(apiService.getItems()).thenReturn(Single.just(itemList))
        subject().fetch().subscribe(result)
        result.assertValue(
            listOf(
                ItemSummary(itemList.items[0].id, itemList.items[0].title),
                ItemSummary(itemList.items[1].id, itemList.items[1].title)
            )
        )
    }

    @Test
    fun `can fail`() {
        val throwable = Throwable()
        `when`(apiService.getItems()).thenReturn(Single.error(throwable))

        subject().fetch().subscribe(result)

        result.assertError(throwable)
    }

    @Test
    fun `can manage fail`() {
        val throwable = Throwable()
        `when`(apiService.getItems()).thenReturn(Single.error(throwable))
        val errorHandler = object:  RetryManager {
            override fun retries(errorFlowable: Flowable<Throwable>): Publisher<RetryEvent> = Publisher {  }
        }

        subject(errorHandler).fetch().subscribe(result)

        result.assertNoErrors()
    }
}
