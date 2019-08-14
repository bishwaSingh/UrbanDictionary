package awesome.shizzle.urbandictionary.network

import awesome.shizzle.urbandictionary.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {
    @GET("3/trending/movie/week")
    suspend fun trendingMoviesForTheWeek(@Query("page") pageNumber: Int): MoviesResponse

    @GET("3/search/movie?language=en-US&include_adult=false")
    suspend fun searchForMovies(@Query("query") query: String, @Query("page") pageNumber: Int): MoviesResponse
}