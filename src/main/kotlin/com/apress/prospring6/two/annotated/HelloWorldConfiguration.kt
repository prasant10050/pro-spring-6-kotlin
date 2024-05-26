package com.apress.prospring6.two.annotated

import com.apress.prospring6.decoupled.HelloWorldMessageProvider
import com.apress.prospring6.decoupled.MessageProvider
import com.apress.prospring6.decoupled.MessageRenderer
import com.apress.prospring6.decoupled.StandardOutMessageRenderer
import org.springframework.context.annotation.Bean

open class HelloWorldConfiguration {
    @Bean
    open fun provider():MessageProvider{
        return HelloWorldMessageProvider()
    }
    @Bean
    open fun renderer(): MessageRenderer {
        val renderer:MessageRenderer = StandardOutMessageRenderer()
        renderer.messageProvider= provider()
        return renderer
    }
}