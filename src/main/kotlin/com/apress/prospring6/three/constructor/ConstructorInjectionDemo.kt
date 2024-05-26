package com.apress.prospring6.three.constructor

import com.apress.prospring6.decoupled.MessageRenderer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ConstructorInjectionDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx:ApplicationContext= AnnotationConfigApplicationContext(HelloWorldConfiguration::class.java)
        val mr:MessageRenderer=ctx.getBean("renderer", MessageRenderer::class.java)
        mr.render()
    }
}