package com.mbw.squarerepos.base.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.mbw.squarerepos.base.constant.KEY_DARK_MODE
import com.mbw.squarerepos.base.constant.KEY_LANGUAGE
import com.mbw.squarerepos.base.constant.PREFERENCE_NAME
import java.util.Locale


/**
 Represents a base class for activities in the app that initializes and handles view binding.
 Supports data binding with a generic type for flexibility across different activities.
 Supports configuration changes for language and light/dark themes.

 @param layoutId The resource ID of the layout this activity will use.
 @property _binding Internal property to hold the binding instance which is initialized during onCreate.
 @property binding Public accessor for _binding ensuring it is never accessed when null.

 @see AppCompatActivity
 @see ViewBinding
*/
open class BaseActivity<out Binding : ViewBinding>(private val layoutId: Int): AppCompatActivity() {

    private var _binding: Binding? = null

    // This property is only valid between onCreate and
    // onDestroy.
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load preferences
        loadAndApplyPreferences()

        _binding = DataBindingUtil.setContentView(this, layoutId)

        setContentView(binding.root)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    /**
     * Load the stored preferences for language and theme.
     */
    private fun loadAndApplyPreferences() {
        val sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val language = sharedPreferences.getString(KEY_LANGUAGE, Locale.getDefault().language) ?: Locale.getDefault().language
        val isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)

        // Apply language
//        setLocale(language)

        // Apply theme
        setThemeMode(isDarkMode)
    }

    /**
     * Set the app's locale based on the provided language code.
     *
     * @param language The language code to set.
     */
    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        applyOverrideConfiguration(config)
    }

    /**
     * Set the app's theme mode.
     *
     * @param isDarkMode True to enable dark mode, false to enable light mode.
     */
    private fun setThemeMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
