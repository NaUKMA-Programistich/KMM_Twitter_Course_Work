package ua.ukma.edu.programistich.kmmtwitter.common

import platform.Foundation.NSMutableCharacterSet
import platform.Foundation.NSString
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters

actual object PlatformEncoder {
    actual fun encode(str: String): String {
        val objcString = NSString.create(string = str)
        val characterSet = NSMutableCharacterSet()
        characterSet.addCharactersInString("!*'();:@&=+$,/?%#[]")
        return objcString.stringByAddingPercentEncodingWithAllowedCharacters(characterSet.invertedSet) ?: str
    }
}
