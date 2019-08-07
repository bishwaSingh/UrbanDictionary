package awesome.shizzle.urbandictionary.model

import kotlinx.serialization.Serializable
import java.sql.Date
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Response(val list: List<UrbanDefinition>) {

    @Serializable
    class UrbanDefinition(
        val definition: String,
        val permalink: String,
        val thumbs_up: Int,
        val sound_urls: List<String>,
        val author: String,
        val word: String,
        val defid: Long,
        val current_vote: String,
        val written_on: String,
        val example: String,
        val thumbs_down: Int
    ) {
        fun thumbsUpAsString() = thumbs_up.toString()
        fun thumbsDownAsString() = thumbs_down.toString()

        fun writtenByOnAsString(): String {
            return try {
                val parsedDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.US).parse(written_on)
                val toFormat = SimpleDateFormat("LLLL dd, Y", Locale.US)
                val newFormattedDate = toFormat.format(parsedDateFormat)
                "$author\non $newFormattedDate"
            } catch (pe: ParseException) {
                "by $author"
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as UrbanDefinition

            if (definition != other.definition) return false
            if (permalink != other.permalink) return false
            if (thumbs_up != other.thumbs_up) return false
            if (sound_urls != other.sound_urls) return false
            if (author != other.author) return false
            if (word != other.word) return false
            if (defid != other.defid) return false
            if (current_vote != other.current_vote) return false
            if (written_on != other.written_on) return false
            if (example != other.example) return false
            if (thumbs_down != other.thumbs_down) return false

            return true
        }
    }
}