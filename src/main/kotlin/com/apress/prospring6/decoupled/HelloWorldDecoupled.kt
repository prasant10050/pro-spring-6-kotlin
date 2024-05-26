package com.apress.prospring6.decoupled

object HelloWorldDecoupled {
    @JvmStatic
    fun main(args: Array<String>) {
        val mr:MessageRenderer=StandardOutMessageRenderer()
        val mp:MessageProvider=HelloWorldMessageProvider()
        mr.messageProvider=mp
        mr.render()
    }
}