package awesome.shizzle.urbandictionary.network

import okhttp3.Interceptor
import okhttp3.Response

class MDBApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .url(
                    chain.request().url().newBuilder().addQueryParameter(
                        "api_key",
                        "5627d598fad4a160ee7331e2a4ddc54b"
                    ).build()
                ).build()
        )
    }
}