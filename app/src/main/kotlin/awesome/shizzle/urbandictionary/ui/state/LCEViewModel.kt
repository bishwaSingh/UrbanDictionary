package awesome.shizzle.urbandictionary.ui.state

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class LCEViewModel<C, T>(
    protected val loading: MutableLiveData<Boolean> = MutableLiveData(),
    protected val error: MutableLiveData<T> = MutableLiveData(),
    protected val content: MutableLiveData<C> = MutableLiveData()
) : ViewModel() {
    val loadingState: LiveData<Boolean> = loading

    init {
        loading.value = false
    }

    protected fun loadData(
        incoming: C? = null,
        makeNetworkCall: suspend (incomingContent: C?) -> C,
        onSuccess: (networkResponse: C) -> Unit
    ) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val results = runCatching {
                makeNetworkCall(incoming)
            }
            if (results.isSuccess) {
                onSuccess(results.getOrThrow())
            } else if (results.isFailure) {
                (results.exceptionOrNull() as? T)?.let {
                    if (it is Throwable)
                        Log.e(TAG, "Error deets - ", it)
                    error.postValue(it)
                }
            }
            loading.postValue(false)
        }
    }

    protected fun loadData(
        incoming: C? = null,
        makeNetworkCall: suspend (incomingContent: C?) -> C,
        onSuccess: (networkResponse: C) -> Unit,
        onFailure: (error: Throwable?) -> T
    ) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val results = runCatching {
                makeNetworkCall(incoming)
            }
            if (results.isSuccess) {
                onSuccess(results.getOrThrow())
            } else if (results.isFailure) {
                val exception = results.exceptionOrNull()
                if (exception != null) {
                    Log.e(TAG, "Error deets - ", exception)
                    error.postValue(onFailure(exception))
                }
            }
            loading.postValue(false)
        }
    }

    companion object {
        const val TAG = "LCEViewModel"
    }
}