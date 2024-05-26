package com.apress.prospring6.decoupled

class HelloWorldMessageProvider : MessageProvider {
    init {
        println("--> Hello World MessageProvider:: constructor called")
    }

    override val message: String
        get() = "Hello world!"
}