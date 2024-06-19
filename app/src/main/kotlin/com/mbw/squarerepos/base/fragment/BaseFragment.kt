package com.mbw.squarerepos.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withResumed
import androidx.viewbinding.ViewBinding
import com.mbw.squarerepos.base.constant.BASE_URL
import com.mbw.squarerepos.base.constant.INTERNET_STATUS_CHECK_INTERVAL
import com.mbw.squarerepos.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * BaseFragment is an open class that all fragments in the application can inherit from. It handles view binding
 * and provides a method to handle errors.
 *
 * @param Binding the type of ViewBinding associated with the fragment
 * @param layoutId the layout resource ID of the fragment
 */
open class BaseFragment<out Binding : ViewBinding>(private val layoutId: Int) : Fragment() {

    private var _binding: Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    protected val binding get() = _binding!!

    private var lastInternetStatus: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        startInternetStatusChecking()
        return binding.root
    }

    private fun startInternetStatusChecking() {
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                withResumed { }
                val currentStatus = isInternetAvailable()
                if (currentStatus != lastInternetStatus) {
                    lastInternetStatus = currentStatus
                    withContext(Dispatchers.Main) {
                        onInternetStatusChanged(currentStatus)
                    }
                }
                delay(INTERNET_STATUS_CHECK_INTERVAL)
            }
        }
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val connection = URL(BASE_URL).openConnection() as HttpURLConnection
            connection.connectTimeout = 1500
            connection.connect()
            connection.responseCode == 200
        } catch (e: IOException) {
            false
        }
    }

    /**
     * This open function gets called when the internet state changes.
     * Any fragment extending BaseFragment can override this to know if it's connected or not.
     */
    protected open fun onInternetStatusChanged(isConnected: Boolean) {
        // Can be overridden by subclasses
    }

    /**
     * Handles errors by showing a Toast message with the error's message.
     *
     * @param error the Resource.Error object containing the error information
     */
    protected fun handleError(error: Resource.Error<*>) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}