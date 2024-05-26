package com.apress.prospring6.decoupled

import com.apress.prospring6.three.Container
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("renderer")
class StandardOutMessageRenderer :
    MessageRenderer {
    override var messageProvider: MessageProvider? = null
        @Autowired set(value) {
            field = value
        }

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

    fun setMessageProvider(messageProvider: MessageProvider?) {
        this.messageProvider = messageProvider
    }
}