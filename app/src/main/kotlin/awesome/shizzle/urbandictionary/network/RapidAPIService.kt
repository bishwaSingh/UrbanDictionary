package awesome.shizzle.urbandictionary.network

import awesome.shizzle.urbandictionary.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidAPIService {
    //    @GET("debug/def.json")
    @GET("define")
    suspend fun getDefinitions(@Query("term") term: String): Response
}