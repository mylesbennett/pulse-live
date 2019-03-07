package com.aimicor.pulselive.interactor

import com.aimicor.pulselive.ApiService
import com.aimicor.pulselive.InteractorSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
abstract class ApiServiceTest<T : ApiServiceInteractor>(
    private val classToken: Class<T>
) {
    @Mock
    private lateinit var schedulers: InteractorSchedulers

    @Mock
    protected lateinit var apiService: ApiService

    protected fun subject(retryManager: RetryManager? = null): T {
        val subject: T = if(retryManager ==  null) spy(classToken)
        else spy(classToken.getConstructor(RetryManager::class.java).newInstance(retryManager))
        Mockito.`when`(subject.apiService).thenReturn(apiService)
        Mockito.`when`(subject.schedulers).thenReturn(schedulers)
        Mockito.`when`(schedulers.io()).thenReturn(Schedulers.trampoline())
        return subject
    }
}
