package com.apress.prospring6.two

import com.apress.prospring6.decoupled.MessageRenderer
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

object HelloWorldSpringDI {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: ApplicationContext = ClassPathXmlApplicationContext("spring/app-context.xml")
        var mr:MessageRenderer=ctx.getBean("renderer",MessageRenderer::class.java)
        mr.render()
    }
}