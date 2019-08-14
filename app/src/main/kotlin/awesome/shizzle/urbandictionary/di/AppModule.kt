package awesome.shizzle.urbandictionary.di

import awesome.shizzle.urbandictionary.ui.state.DefinitionsViewModel
import awesome.shizzle.urbandictionary.ui.state.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DefinitionsViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
}