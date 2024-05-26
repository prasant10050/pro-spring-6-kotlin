package com.apress.prospring6.three.annotated

import com.apress.prospring6.decoupled.MessageRenderer
import com.apress.prospring6.three.constructor.HelloWorldConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object HelloWorldSpringAnnotated {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx:ApplicationContext= AnnotationConfigApplicationContext(
            HelloWorldConfiguration::class.java)
        val mr:MessageRenderer=ctx.getBean("renderer",MessageRenderer::class.java)
        mr.render()
    }
}