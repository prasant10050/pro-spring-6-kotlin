package com.apress.prospring6.decoupled

import com.apress.prospring6.three.Container

class StandardOutMessageRenderer : MessageRenderer {
    override var messageProvider: MessageProvider? = null
    override fun render() {
        println(
            messageProvider?.message ?: throw RuntimeException(
                "You must set the property messageProvider of class:" + StandardOutMessageRenderer::class.java.name
            )
        )
    }

    override fun performLookup(container: Container) {
        this.messageProvider =
            container.getDependency("provider") as MessageProvider
    }

    init {
        println("--> StandardOutMessageRenderer:: constructor called")
    }
}