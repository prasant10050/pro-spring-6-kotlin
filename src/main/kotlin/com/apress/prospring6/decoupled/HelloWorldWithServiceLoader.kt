package com.apress.prospring6.decoupled

import java.util.*

object HelloWorldWithServiceLoader {
    @JvmStatic
    fun main(args: Array<String>) {
        val slr: ServiceLoader<MessageRenderer> =
            ServiceLoader.load(MessageRenderer::class.java)
        val slp: ServiceLoader<MessageProvider> =
            ServiceLoader.load(MessageProvider::class.java)

        val mr: MessageRenderer = slr.findFirst().orElseThrow() {
            IllegalArgumentException("Service of type 'MessageRenderer' was not found!")
        }
        val mp: MessageProvider = slp.findFirst().orElseThrow() {
            IllegalArgumentException("Service of type 'MessageProvider' was not found!")
        }

        mr.messageProvider = mp
        mr.render()
    }
}