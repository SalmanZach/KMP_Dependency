package com.salmanzach.deps

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform}!"
    }
}