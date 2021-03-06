package com.example.jeffaccount.ui.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jeffaccount.JeffApplication
import com.example.jeffaccount.JeffRepository
import com.example.jeffaccount.dataBase.LogInCred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: JeffApplication) : AndroidViewModel(application) {

    private val repository = JeffRepository(application)

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _logInCredDeleted = MutableLiveData<Boolean>()
    val logInCredDeleted: LiveData<Boolean>
        get() = _logInCredDeleted

    init {
        _logInCredDeleted.value = false
    }

    fun deleteLogInCred(logInCred: LogInCred) {
        uiScope.launch {
            repository.deleteLogInCred(logInCred)
            _logInCredDeleted.value = true
        }
    }

    fun loginCredDeleted(){
        _logInCredDeleted.value = false
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

