package awesome.shizzle.urbandictionary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import awesome.shizzle.urbandictionary.databinding.FragmentMovieDetailBinding
import awesome.shizzle.urbandictionary.model.Movie
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MovieInfoFragment : BottomSheetDialogFragment() {

    companion object {
        private const val EXTRA_MOVIE = "extra_movie"
        fun newInstance(movie: Movie): MovieInfoFragment {
            return MovieInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.movie = requireArguments().getParcelable<Movie>(EXTRA_MOVIE)
            .also {
                binding.imageView.load(it.fullBackdropPath())
            }
        return binding.root
    }
}