package com.example.marvel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.data.interactor.CharactersInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val charactersInteractor: CharactersInteractor by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val disposable = CompositeDisposable()

        disposable.add(charactersInteractor.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                tv_main.text = it.toString()
            }
            .subscribe({
                val items = it.data.results
                val adapter = CharacthersListAdapter(items)

                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(this@MainActivity)

                rv_main.setLayoutManager(layoutManager)

                rv_main.setAdapter(adapter)
//                tv_main.text = it.toString()
            }, {
                Log.e("ERRO", it.toString())
            })
        )

    }
}