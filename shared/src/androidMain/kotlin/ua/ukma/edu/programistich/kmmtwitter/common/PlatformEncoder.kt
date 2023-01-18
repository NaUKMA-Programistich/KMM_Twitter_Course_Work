package ua.ukma.edu.programistich.kmmtwitter.common

import java.net.URLEncoder

actual object PlatformEncoder {
    actual fun encode(str: String): String {
        return URLEncoder.encode(str, "UTF-8")
    }
}