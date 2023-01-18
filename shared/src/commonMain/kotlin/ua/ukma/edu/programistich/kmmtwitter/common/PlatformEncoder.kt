package ua.ukma.edu.programistich.kmmtwitter.common

expect class PlatformEncoder constructor(){
    fun encode(str: String): String
}