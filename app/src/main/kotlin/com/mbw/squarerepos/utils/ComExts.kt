package com.mbw.squarerepos.utils

import android.util.Log

fun Any.logLong(tag: String, veryLongString: String) {
    val maxLogSize = 1000
    for (i in 0..veryLongString.length / maxLogSize) {
        val start = i * maxLogSize
        var end = (i + 1) * maxLogSize
        end = if (end > veryLongString.length) veryLongString.length else end
        Log.v(tag, veryLongString.substring(start, end))
    }
}