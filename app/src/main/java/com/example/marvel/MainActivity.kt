package com.example.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.data.interactor.CharactersInteractor
import com.google.android.material.snackbar.Snackbar
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

        disposable.add(charactersInteractor.execute(20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { pb_main.isVisible = true }
            .doOnError {
                Snackbar.make(
                    window.decorView.rootView,
                    "Error ${it.message}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            .subscribe {
                pb_main.isVisible = false
                val items = it.data.results
                val adapter = CharactersListAdapter(items)

                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(this@MainActivity)

                rv_main.layoutManager = layoutManager

                rv_main.adapter = adapter
            }
        )

//        rv_main.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                if (dy > 0) {
//                    disposable.add(charactersInteractor.execute(20)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnError {
//                            Snackbar.make(
//                                window.decorView.rootView,
//                                "Error ${it.message}",
//                                Snackbar.LENGTH_LONG
//                            ).show()
//                        }
//                        .subscribe {
//                            val items = it.data.results
//                            val adapter = CharactersListAdapter(items)
//
//                            val layoutManager: RecyclerView.LayoutManager =
//                                LinearLayoutManager(this@MainActivity)
//
//                            rv_main.layoutManager = layoutManager
//
//                            rv_main.adapter = adapter
//
//                        }
//                    )
//                }
//            }})

    }
}