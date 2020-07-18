package com.example.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.model.Result
import com.example.marvel.MainState.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private var offset = 0
    private val limit = 20
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = CharactersListAdapter(mutableListOf())
        adapter.items.clear()
        adapter.notifyDataSetChanged()

        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this@MainActivity)

        rv_main.layoutManager = layoutManager

        rv_main.adapter = adapter

        viewModel.loadCharacters(limit, 0)
        viewModel.state.observe(this, androidx.lifecycle.Observer { state ->
            when (state) {
                is Loading -> onLoading()
                is Success -> onSuccess(state.result)
                is Error -> onError(state.exception.message)
            }
        })

        rv_main.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    var visibleItems = 0
                    var totalItems = 0
                    var firstVisibleItem = 0

                    layoutManager?.also {
                        visibleItems = it.childCount
                        totalItems = it.itemCount
                        firstVisibleItem =
                            (it as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    }

                    if (isReachingToTheEndOfList(firstVisibleItem, visibleItems, totalItems)) {
                        offset += 20
                        viewModel.loadCharacters(limit, offset)
                    }
                }
            }
        })

    }

    override fun onStop() {
        super.onStop()
        viewModel.disposable.dispose()
    }

    private fun isReachingToTheEndOfList(
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ): Boolean {
        return (totalItemCount > 0 &&
                firstVisibleItem + visibleItemCount + 5 > totalItemCount)
    }

    private fun dismissLoading() {
        pb_main.isVisible = false
    }

    private fun onLoading() {
        pb_main.isVisible = true
    }

    private fun onError(message: String?) {
        dismissLoading()
        Snackbar.make(
            window.decorView.rootView,
            "Error: $message",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onSuccess(result: MutableList<Result>) {
        dismissLoading()
        val adapter = rv_main.adapter as CharactersListAdapter
        adapter.items.addAll(result)
        adapter.notifyDataSetChanged()
    }
}