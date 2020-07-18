package com.example.marvel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel.MainState.*
import com.example.marvel.data.interactor.CharactersInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(val charactersInteractor: CharactersInteractor) : ViewModel() {
    val disposable = CompositeDisposable()
    private var _state: MutableLiveData<MainState> = MutableLiveData()

    val state: LiveData<MainState>
        get() = _state

    fun loadCharacters(limit: Int, offset: Int) {
        disposable.add(charactersInteractor.execute(limit, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.value = Loading }
            .doOnError { _state.value = Error(it) }
            .subscribe {
                _state.value = Success(it.data.results.toMutableList())
            }
        )
    }
}