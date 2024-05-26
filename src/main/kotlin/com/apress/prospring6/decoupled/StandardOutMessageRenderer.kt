package com.apress.prospring6.decoupled

import com.apress.prospring6.three.Container
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("renderer")
class StandardOutMessageRenderer @Autowired constructor(override var messageProvider: MessageProvider? ):
    MessageRenderer {

    override fun render() {
        println(
            messageProvider?.message ?: throw RuntimeException(
                "You must set the property messageProvider of class:" + StandardOutMessageRenderer::class.java.name
            )
        )
    }

    init {
        println("--> StandardOutMessageRenderer:: constructor called")
    }
}