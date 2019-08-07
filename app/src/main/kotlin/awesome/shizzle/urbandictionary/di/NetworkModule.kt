package awesome.shizzle.urbandictionary.di

import awesome.shizzle.urbandictionary.network.CachingInterceptor
import awesome.shizzle.urbandictionary.network.HeaderInterceptor
import awesome.shizzle.urbandictionary.network.RapidAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single {

        val okClient = OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, 5 * 1024 * 1024))
            .addInterceptor(CachingInterceptor(androidContext()))
            .addNetworkInterceptor(HeaderInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .client(okClient)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }
    single { (get() as Retrofit).create(RapidAPIService::class.java) }
}