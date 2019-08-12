package awesome.shizzle.urbandictionary.network

import android.content.Context
import kotlinx.io.IOException
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import java.util.concurrent.TimeUnit
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService


class CachingInterceptor(context: Context) : Interceptor {
    private val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as? ConnectivityManager

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()

        request = Request.Builder()
            .cacheControl(
                CacheControl.Builder()
                    .maxAge(1, TimeUnit.DAYS)
                    .minFresh(4, TimeUnit.HOURS)
                    .maxStale(8, TimeUnit.HOURS)
                    .build()
            )
            .url(request.url())
            .build()


        return chain.proceed(request)
    }

    private fun isConnected(): Boolean {
        try {
            return connectivityManager?.activeNetworkInfo?.isConnected == true
        } catch (e: Exception) {
            Log.w("CachingInterceptor", e.toString())
        }

        return false
    }
}