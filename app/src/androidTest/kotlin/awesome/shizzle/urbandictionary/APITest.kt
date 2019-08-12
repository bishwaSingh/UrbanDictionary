package awesome.shizzle.urbandictionary

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import awesome.shizzle.urbandictionary.di.networkModule
import awesome.shizzle.urbandictionary.model.Response
import awesome.shizzle.urbandictionary.network.RapidAPIService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
@SmallTest
class APITest : KoinTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val rapidAPIService: RapidAPIService by inject()

    @Before
    fun startKoin() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()

        stopKoin()
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
        }
    }

    @Test
    fun searchForLol() = runBlocking {
        assertThat(rapidAPIService).isNotNull()
        val response = rapidAPIService.getDefinitions("lol")
        assertThat(response).isInstanceOf(Response::class.java)
        assertThat(response.list).isNotEmpty()
    }

    @After
    fun stop() {
        stopKoin()
    }

}