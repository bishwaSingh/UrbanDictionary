package awesome.shizzle.urbandictionary.di

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import awesome.shizzle.urbandictionary.di.appModule
import awesome.shizzle.urbandictionary.di.networkModule
import awesome.shizzle.urbandictionary.network.RapidAPIService
import awesome.shizzle.urbandictionary.ui.state.DefinitionsViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleDITest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    @Test
    fun checkInitialInjections() {
        stopKoin()

        startKoin {
            androidContext(context)
            modules(listOf(networkModule, appModule))
        }
        val defVM: DefinitionsViewModel = get()

        assertThat(defVM).isNotNull()
        assertThat(defVM.errorState.value).isNull()
        assertThat(defVM.content.value).isNull()
        stopKoin()
    }


}