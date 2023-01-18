package ua.ukma.edu.programistich.kmmtwitter.common

import java.net.URLEncoder

actual class PlatformEncoder {
    actual fun encode(str: String): String {
        return URLEncoder.encode(str, "UTF-8")
    }
}