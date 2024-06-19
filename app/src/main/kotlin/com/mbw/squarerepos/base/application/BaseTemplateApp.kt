package com.mbw.squarerepos.base.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Base application class for the template Kotlin application.
 * This class sets up any necessary configurations for the application
 * and integrates with Dagger Hilt for dependency injection.
 *
 * This class serves as the base entry point of the application.
 */
@HiltAndroidApp
open class BaseTemplateApp : Application()
