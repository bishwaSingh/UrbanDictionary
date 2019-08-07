package awesome.shizzle.urbandictionary.ui.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import awesome.shizzle.urbandictionary.R
import awesome.shizzle.urbandictionary.ui.adapter.DefinitionsListAdapter
import awesome.shizzle.urbandictionary.ui.state.DefinitionsViewModel
import awesome.shizzle.urbandictionary.ui.view.decorator.DefinitionItemDecorator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val definitionsViewModel: DefinitionsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.adapter = DefinitionsListAdapter()
        recyclerview.addItemDecoration(DefinitionItemDecorator(resources.getDimension(R.dimen.list_item_margin).toInt()))

        definitionsViewModel.loadingState.observe(this, Observer {
            if (it) persistentSearchView.showProgressBar()
            else persistentSearchView.hideProgressBar()
        })

        definitionsViewModel.content.observe(this, Observer {
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
