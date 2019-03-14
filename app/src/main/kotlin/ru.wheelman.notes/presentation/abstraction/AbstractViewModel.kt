package ru.wheelman.notes.presentation.abstraction

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.wheelman.notes.R
import ru.wheelman.notes.model.entities.Result

abstract class AbstractViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _showError = MutableLiveData<String>()
    internal val showError: LiveData<String> = _showError

    private fun onError() {
        _showError.value = app.getString(R.string.something_went_wrong)
    }

    protected abstract fun onSuccess(data: Any)

    protected fun processResult(result: Result) {
        when (result) {
            is Result.Success<*> -> onSuccess(result.data)
            is Result.Error -> onError()
        }
    }
}