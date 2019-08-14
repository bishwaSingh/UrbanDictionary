package awesome.shizzle.urbandictionary.ui.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import awesome.shizzle.urbandictionary.model.MoviesResponse
import awesome.shizzle.urbandictionary.network.TMDBAPIService

class MoviesViewModel(private val tmdbapiService: TMDBAPIService) : LCEViewModel<MoviesResponse, Throwable?>() {

    val errorState: LiveData<Throwable?> = error

    private val trendingContent = MutableLiveData<MoviesResponse>()
    val trendingMovies = Transformations.map(trendingContent) {
        it.results
    }

    val searchedContent = Transformations.map(content) {
        it.results
    }

    init {
        initialLoadTrendingMovies()
    }

    private var searchQuery = ""

    private fun initialLoadTrendingMovies() {
        loadData(makeNetworkCall = {
            tmdbapiService.trendingMoviesForTheWeek(1)
        }, onSuccess = {
            trendingContent.postValue(it)
        })
    }

    fun loadNextPage() {
        //only one call has to go out at any given time. Figure out how to identifying a running job
        if (searchQuery.isBlank()) {
            loadNextTrendingPage()
        } else {
            loadNextSearchPage()
        }
    }

    private fun loadNextTrendingPage() {
        //TODO figure out a way to avoid the paginated call if its already running by holding on to a job reference or something
        trendingContent.value?.let { previousTrendingContent ->
            loadData(previousTrendingContent, { incomingTrendingContent ->
                incomingTrendingContent?.let {
                    tmdbapiService.trendingMoviesForTheWeek(it.page + 1)
                } ?: run {
                    throw RuntimeException("This should never happen, if it does, derrp, go learn some kotlin brah! :P")
                }
            }, {
                trendingContent.postValue(previousTrendingContent + it)
            })
        }
    }

    fun searchForMovie(query: String) {
        if (query.isBlank()) {
            trendingContent.value = trendingContent.value
            searchQuery = ""
        } else {
            loadData(makeNetworkCall = {
                tmdbapiService.searchForMovies(query, 1)
            }, onSuccess = {
                searchQuery = query
                content.postValue(it)
            })
        }
    }

    private fun loadNextSearchPage() {
        //TODO figure out a way to avoid the paginated call if its already running by holding on to a job reference or something
        content.value?.let { previousSearchContent ->
            loadData(previousSearchContent, { incomingContent ->
                incomingContent?.let {
                    tmdbapiService.searchForMovies(searchQuery, it.page + 1)
                } ?: run {
                    throw RuntimeException("This should never happen, if it does, derrp, go learn some kotlin brah! :P")
                }
            }, {
                content.postValue(previousSearchContent + it)
            })
        }
    }

}