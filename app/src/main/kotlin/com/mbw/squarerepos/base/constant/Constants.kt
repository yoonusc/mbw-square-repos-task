package com.mbw.squarerepos.base.constant

/**
 * Constants used throughout the application.
 */
const val BASE_URL = "https://api.github.com/orgs/square/"

/**
 * Timeout duration for establishing a connection, in seconds.
 */
const val TIMEOUT_CONNECT_SECONDS = 20L

/**
 * Timeout duration for reading data, in seconds.
 */
const val TIMEOUT_READ_SECONDS = 20L

/**
 * Timeout duration for writing data, in seconds.
 */
const val TIMEOUT_WRITE_SECONDS = 20L

/**
 * Name of the database.
 */
const val DB_NAME = "square_github_repos_db"

/**
 * Shared Preferences Name
 */
const val PREFERENCE_NAME = "AppPreferences"

/**
 * Key for language preference
 */
const val KEY_LANGUAGE = "language"

/**
 * Key for dark mode preference
 */
const val KEY_DARK_MODE = "dark_mode"

/**
 * Internet status check interval in milliseconds.
 */
const val INTERNET_STATUS_CHECK_INTERVAL = 10000L
