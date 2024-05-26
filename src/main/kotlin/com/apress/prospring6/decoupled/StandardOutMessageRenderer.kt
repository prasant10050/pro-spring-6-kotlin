package com.apress.prospring6.decoupled

class StandardOutMessageRenderer : MessageRenderer {
    override fun render() {
        println(
            messageProvider?.message ?: throw RuntimeException(
                "You must set the property messageProvider of class:" + StandardOutMessageRenderer::class.java.name
            )
        )
    }

    override var messageProvider: MessageProvider? = null
        set(value) {
            field = value
            println("--> StandardOutMessageRenderer::setting the provider")
        }

    init {
        println("--> StandardOutMessageRenderer:: constructor called")
    }
}