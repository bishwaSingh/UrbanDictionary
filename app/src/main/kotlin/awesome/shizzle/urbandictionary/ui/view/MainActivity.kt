package awesome.shizzle.urbandictionary.ui.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import awesome.shizzle.urbandictionary.R
import awesome.shizzle.urbandictionary.ui.adapter.DefinitionsListAdapter
import awesome.shizzle.urbandictionary.ui.state.DefinitionsViewModel
import awesome.shizzle.urbandictionary.ui.view.decorator.DefinitionItemDecorator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val definitionsViewModel by viewModel<DefinitionsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recyclerview) {
            adapter = DefinitionsListAdapter()
            addItemDecoration(DefinitionItemDecorator(context))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        definitionsViewModel.loadingState.observe(this, Observer {
            if (it) persistentSearchView.showProgressBar()
            else persistentSearchView.hideProgressBar()
        })

        definitionsViewModel.contentState.observe(this, Observer {
            (recyclerview.adapter as DefinitionsListAdapter).submitList(it)
            recyclerview.requestFocus()
        })

        definitionsViewModel.errorState.observe(this, Observer {
            Snackbar.make(container, it, Snackbar.LENGTH_LONG).show()
        })

        with(persistentSearchView) {

            setOnSearchConfirmedListener { searchView, query ->
                searchWithQuery(searchView, query)
            }

            showRightButton()

            setOnLeftBtnClickListener {
                searchWithQuery(persistentSearchView, persistentSearchView.inputQuery)
            }

            setOnRightBtnClickListener {
                MaterialAlertDialogBuilder(this@MainActivity).setCancelable(true)
                    .setSingleChoiceItems(
                        arrayOf("Thumbs up", "Thumbs Down"),
                        definitionsViewModel.selectedSort.ordinal
                    ) { dialog, index ->
                        dialog.dismiss()
                        definitionsViewModel.sortWith(index)
                    }.show()
            }

            setRightButtonDrawable(R.drawable.ic_sort_black_24dp)

            setQueryInputHint("Search in Urban Dictionary")
        }
    }

    private fun searchWithQuery(view: View, query: String) {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view.windowToken,
            0
        )
        view.clearFocus()
        definitionsViewModel.searchFor(query)
    }
}
