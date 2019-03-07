package com.aimicor.pulselive.viewmodel.main

import androidx.lifecycle.ViewModel
import com.aimicor.pulselive.viewmodel.ApiInteractors
import com.aimicor.pulselive.viewmodel.ViewModelSchedulers
import com.aimicor.pulselive.viewmodel.detail.DetailViewModel
import com.aimicor.pulselive.viewmodel.detail.DetailViewModelFactory
import com.aimicor.pulselive.viewmodel.parent.ParentScope
import com.aimicor.pulselive.viewmodel.parent.ParentViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.io.Serializable
import javax.inject.Inject

@ParentScope
class MainViewModel @Inject constructor(
    val adapter: MainAdapter,
    apiInteractors: ApiInteractors,
    schedulers: ViewModelSchedulers,
    detailViewModelFactory: DetailViewModelFactory,
    parentViewModel: ParentViewModel
) : ViewModel(), Serializable {

    private val listItemClickSubject = PublishSubject.create<DetailViewModel>()

    val listItemClickEvent: Observable<DetailViewModel> = listItemClickSubject

    private val disposables = CompositeDisposable(

        apiInteractors.items.fetch()
            .observeOn(schedulers.mainThread())
            .doOnSuccess { parentViewModel.showProgress(false) }
            .subscribe { list -> adapter.submitList(list) },

        adapter.clickEvent
            .doOnNext { parentViewModel.showProgress(true) }
            .switchMap { detailViewModelFactory.create(it).detailViewModelEvent }
            .doOnNext { parentViewModel.showProgress(false) }
            .subscribe { listItemClickSubject.onNext(it) }
    )

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
