package com.salmanzach.deps

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform