package awesome.shizzle.urbandictionary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.max
import kotlin.math.min

@Serializable
data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    operator fun plus(toAppend: MoviesResponse) = MoviesResponse(
        max(page, toAppend.page),
        results + toAppend.results,
        min(totalPages, toAppend.totalPages),
        min(totalResults, toAppend.totalResults)
    )
}

@Serializable
@Parcelize
data class Movie(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    val popularity: Double
) : Parcelable {
    fun fullPosterPath(width: Int) = "https://image.tmdb.org/t/p/w$width$posterPath"
    fun fullBackdropPath() = "https://image.tmdb.org/t/p/original/$backdropPath"
}