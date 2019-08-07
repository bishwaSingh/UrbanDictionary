package awesome.shizzle.urbandictionary.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6678c207a5mshb78172728938b9dp14211cjsndf8c16e35e3e")
                .build()
        )

    }
}