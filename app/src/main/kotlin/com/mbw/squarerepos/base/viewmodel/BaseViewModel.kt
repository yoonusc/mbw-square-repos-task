/**
 * BaseViewModel.kt
 * 
 * Base class for ViewModels, providing coroutines setup.
 */
package com.mbw.squarerepos.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    private val job = Job()
    protected val ioScope = CoroutineScope(Dispatchers.IO + job)
    protected val mainScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}