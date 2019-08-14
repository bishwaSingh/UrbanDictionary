package awesome.shizzle.urbandictionary.ui.view

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import awesome.shizzle.urbandictionary.R
import awesome.shizzle.urbandictionary.ui.adapter.MoviesListAdapter
import awesome.shizzle.urbandictionary.ui.fragment.MovieInfoFragment
import awesome.shizzle.urbandictionary.ui.state.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MDBActivity : AppCompatActivity() {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recyclerview) {
            val spanCount = context.resources.getInteger(R.integer.span_count)
            adapter = MoviesListAdapter().apply {
                clickListener.observe(this@MDBActivity, Observer {
                    MovieInfoFragment.newInstance(it).show(supportFragmentManager, "details")
                })
            }
            layoutManager = GridLayoutManager(
                context,
                spanCount,
                RecyclerView.VERTICAL,
                false
            )
            doOnLayout {
                (adapter as MoviesListAdapter).gridItemWidth = it.width / spanCount
            }
            setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val gridLayoutManager = layoutManager as GridLayoutManager
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 10
                ) {
                    moviesViewModel.loadNextPage()
                }
            }
        }
        with(persistentSearchView) {
            setOnSearchConfirmedListener { searchView, query ->
                searchWithQuery(query)
            }

            setOnClearInputBtnClickListener {
                searchWithQuery("")
            }

            setOnLeftBtnClickListener {
                searchWithQuery(persistentSearchView.inputQuery)
            }

            setQueryInputHint("Search for movies")
        }

        moviesViewModel.loadingState.observe(this, Observer {
            if (it) persistentSearchView.showProgressBar()
            else persistentSearchView.hideProgressBar()
        })

        moviesViewModel.errorState.observe(this, Observer {
            Snackbar.make(
                container,
                it?.localizedMessage ?: "Derp, something broke! Get a beer for now",
                Snackbar.LENGTH_LONG
            ).show()
        })

        moviesViewModel.searchedContent.observe(this, Observer {
            (recyclerview.adapter as MoviesListAdapter).submitList(it)
        })

        moviesViewModel.trendingMovies.observe(this, Observer {
            (recyclerview.adapter as MoviesListAdapter).submitList(it)
        })
    }

    private fun searchWithQuery(query: String) {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            persistentSearchView.windowToken,
            0
        )
        persistentSearchView.clearFocus()
        moviesViewModel.searchForMovie(query)
    }
}
