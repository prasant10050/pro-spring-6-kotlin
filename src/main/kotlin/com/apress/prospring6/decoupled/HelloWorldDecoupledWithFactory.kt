package com.apress.prospring6.decoupled

object HelloWorldDecoupledWithFactory {
    @JvmStatic
    fun main(args: Array<String>) {
        val mr:MessageRenderer=MessageSupportFactory.renderer?:throw IllegalArgumentException("Service of type 'MessageRenderer' was not found or initialized")
        val mp:MessageProvider=MessageSupportFactory.provider?:throw IllegalArgumentException("Service of type 'MessageProvider' was not found or initialized")

        mr.messageProvider=mp
        mr.render()
    }
}