package com.apress.prospring6.scope

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component("nonSingleton")
@Scope(scopeName = "prototype")
internal class Singer(@Value("John Mayer") val name: String = "unknown") {
    override fun toString(): String {
        return "${super.toString()}, name=$name"
    }
}

object NoSingletonDemo {
    private val logger:Logger=LoggerFactory.getLogger(NoSingletonDemo::class.java)
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx=AnnotationConfigApplicationContext()
        ctx.register(Singer::class.java)
        ctx.refresh()
        val singer1 = ctx.getBean("nonSingleton", Singer::class.java)
        val singer2 = ctx.getBean("nonSingleton", Singer::class.java)
        logger.info("Identity Equal?: " + (singer1 === singer2))
        logger.info("Value Equal:? " + (singer1 == singer2))
        logger.info(singer1.toString())
        logger.info(singer2.toString())
    }
}