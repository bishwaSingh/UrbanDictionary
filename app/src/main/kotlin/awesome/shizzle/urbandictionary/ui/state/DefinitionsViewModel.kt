package awesome.shizzle.urbandictionary.ui.state

import androidx.lifecycle.*
import awesome.shizzle.urbandictionary.model.Response
import awesome.shizzle.urbandictionary.network.RapidAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefinitionsViewModel(private val rapidAPIService: RapidAPIService) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loading

    private val error = MutableLiveData<Pair<Boolean, Throwable>>()
    val errorState =
        Transformations.map(error) { if (!it.first) "There was a problem getting the results" else it.second.localizedMessage }

    private val urbanDictionaryResponse = MutableLiveData<Response>()
    val content: LiveData<List<Response.UrbanDefinition>> = Transformations.map(urbanDictionaryResponse) { response ->
        when (selectedSort) {
            SortOptions.THUMBS_UP -> response.list.sortedByDescending { it.thumbs_up }
            SortOptions.THUMBS_DOWN -> response.list.sortedByDescending { it.thumbs_down }
            SortOptions.DEFAULT -> response.list
        }

    }

    internal var selectedSort: SortOptions = SortOptions.DEFAULT
        private set

    fun searchFor(word: String) {
        if (word.isBlank()) {
            urbanDictionaryResponse.value = Response(listOf())
        } else {
            loading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = runCatching {
                    val response = rapidAPIService.getDefinitions(word)
                    response
                }
                if (result.isSuccess) {
                    urbanDictionaryResponse.postValue(result.getOrThrow())
                } else if (result.isFailure) {
                    if (result.exceptionOrNull() != null) {
                        error.postValue(Pair(true, result.exceptionOrNull() as Throwable))
                    } else {
                        error.postValue(Pair(false, UnknownError()))
                    }
                }
                loading.postValue(false)
            }
        }
    }

    fun sortWith(selectedIndex: Int) {
        val newSort = SortOptions.values()[selectedIndex]
        if (newSort != selectedSort && urbanDictionaryResponse.value != null) {
            loading.value = true
            selectedSort = newSort
            urbanDictionaryResponse.value =
                Response(urbanDictionaryResponse.value?.list as List<Response.UrbanDefinition>)
            loading.value = false
        }
    }


    enum class SortOptions {
        THUMBS_UP, THUMBS_DOWN, DEFAULT
    }

}