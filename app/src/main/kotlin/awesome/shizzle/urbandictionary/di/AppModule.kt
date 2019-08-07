package awesome.shizzle.urbandictionary.di

import awesome.shizzle.urbandictionary.ui.state.DefinitionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DefinitionsViewModel(get()) }
}