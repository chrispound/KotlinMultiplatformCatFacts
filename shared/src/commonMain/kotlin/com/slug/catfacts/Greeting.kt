package com.slug.catfacts

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}