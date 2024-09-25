package com.tomasmacri.expensapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform