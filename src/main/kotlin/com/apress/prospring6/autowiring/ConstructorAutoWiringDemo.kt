package com.apress.prospring6.autowiring

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.util.UUID

object ConstructorAutoWiringDemo {
    private val logger:Logger=LoggerFactory.getLogger(ConstructorAutoWiringDemo::class.java)
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx=AnnotationConfigApplicationContext(AutowiringCfg::class.java)
        val target=ctx.getBean(Target::class.java)
        logger.info("Created target? {}",target!=null)
        logger.info("Injected bar? {}",target.bar!=null)
         logger.info("Injected fooOne? {}", if (target.fooOne != null) target.fooOne!!.id else "")
        logger.info("Injected fooTwo? {}", if (target.fooTwo != null) target.fooTwo!!.id else "")
    }
}

@Configuration
@ComponentScan
internal open class AutowiringCfg

@Component
@Lazy
internal class Target{
    var fooOne: Foo?=null
    var fooTwo: Foo?=null
    var bar: Bar?=null

    constructor(){
        logger.info("---> Target() called")
    }

    //@Autowired
    constructor(foo: Foo){
        fooOne=foo
        logger.info("---> Target(Foo) called")
    }

    //@Autowired
    constructor(foo: Foo,bar:Bar){
        fooOne=foo
        this.bar=bar
        logger.info("---> Target(Foo,Bar) called")
    }

    companion object{
        private val logger:Logger=LoggerFactory.getLogger(Target::class.java)
    }

}

@Component
internal class Foo{
    var id=UUID.randomUUID().toString().replace("-","").substring(0,8)
}

@Component
internal class Bar{}