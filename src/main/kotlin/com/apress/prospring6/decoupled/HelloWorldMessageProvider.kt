package com.apress.prospring6.decoupled

import org.springframework.stereotype.Component

@Component("provider")
class HelloWorldMessageProvider : MessageProvider {
    init {
        println("--> Hello World MessageProvider:: constructor called")
    }

    override val message: String
        get() = "Hello world!"
}