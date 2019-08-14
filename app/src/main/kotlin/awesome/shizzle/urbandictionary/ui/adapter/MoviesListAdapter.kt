package awesome.shizzle.urbandictionary.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import awesome.shizzle.urbandictionary.R
import awesome.shizzle.urbandictionary.helpers.SingleLiveEvent
import awesome.shizzle.urbandictionary.model.Movie
import coil.api.load
import kotlinx.android.synthetic.main.list_item_movie.view.*
import kotlin.math.ceil
import kotlin.math.min

class MoviesListAdapter :
    ListAdapter<Movie, MoviesListAdapter.MoviesViewHolder>(MoviesDiffUtil) {
    internal var gridItemWidth: Int = 0
        set(value) {
            field = min(((ceil(value / 100.0)) * 100).toInt(), 500)
        }

    val clickListener = SingleLiveEvent<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MoviesViewHolder(inflatedView: View) :
        RecyclerView.ViewHolder(inflatedView) {
        fun bind(movie: Movie) {
            itemView.image.doOnLayout {
                it.updateLayoutParams {
                    //this ratio is from the average aspect ratios seen for the posters
                    height = (itemView.image.width * 1.5).toInt()
                }
            }
            itemView.image.load(movie.fullPosterPath(gridItemWidth)) {
                crossfade(true)
                error(R.drawable.ic_error)
            }

            itemView.image.setOnClickListener {
                clickListener.value = movie
            }
        }

    }

    object MoviesDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}