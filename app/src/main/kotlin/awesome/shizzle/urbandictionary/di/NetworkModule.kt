package awesome.shizzle.urbandictionary.di

import awesome.shizzle.urbandictionary.network.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single<Retrofit>(named("ud")) {
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

    single { get<Retrofit>(named("ud")).create(RapidAPIService::class.java) }

    single<Retrofit>(named("mdb")) {
        val okClient = OkHttpClient.Builder()
            .addInterceptor(CachingInterceptor(androidContext()))
            .addNetworkInterceptor(MDBApiKeyInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okClient)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    single { get<Retrofit>(named("mdb")).create(TMDBAPIService::class.java) }
}