package awesome.shizzle.urbandictionary.di

import awesome.shizzle.urbandictionary.network.HeaderInterceptor
import awesome.shizzle.urbandictionary.network.RapidAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build() }
    single {
        Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .client(get())
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }
    single { (get() as Retrofit).create(RapidAPIService::class.java) }
}