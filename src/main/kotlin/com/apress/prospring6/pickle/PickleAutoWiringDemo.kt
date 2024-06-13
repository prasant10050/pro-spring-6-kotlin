package com.apress.prospring6.pickle

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.*
import java.util.*

object PickleAutoWiringDemo {
    private val logger: org.slf4j.Logger =
        org.slf4j.LoggerFactory.getLogger(PickleAutoWiringDemo::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext(AutoWiringCfg::class.java)
        val target = ctx.getBean(TrickyTarget::class.java)
        logger.info("target: Created target? {}", target != null)
        logger.info("target: Injected bar? {}", target.bar != null)
        logger.info(
            "target: Injected fooOne? {}",
            if (target.fooOne != null) target.fooOne.toString() else ""
        )
        logger.info(
            "target: Injected fooTwo? {}",
            if (target.fooTwo != null) target.fooTwo.toString() else ""
        )
    }
}

interface Foo {
    // Empty or marker interface
}

internal class FooImplOne : Foo {
    var id =
        "one:" + UUID.randomUUID().toString().replace("-", "").substring(0, 8)

    override fun toString(): String {
        return "${super.toString()},id=$id"
    }
}

internal class FooImplTwo : Foo {
    var id =
        "two:" + UUID.randomUUID().toString().replace("-", "").substring(0, 8)

    override fun toString(): String {
        return "${super.toString()},id=$id"
    }
}

class Bar {}

class TrickyTarget {
    var fooOne: Foo? = null
        @Autowired
        @Qualifier("fooImplOne") set(value) {
            field = value
            logger.info("---> Property fooOne set")
        }
    var fooTwo: Foo? = null
        @Autowired
        @Qualifier("fooImplTwo") set(value) {
            field = value
            logger.info("---> Property fooTwo set")
        }
    var bar: Bar? = null
        @Autowired
        set(value) {
            field = value
            logger.info("---> Property bar set")
        }


    init {
        logger.info("---> Target.init() called")
    }

    constructor(){
        logger.info("---> TrickyTarget() called")
    }

    constructor(foo: Foo?) {
        fooOne = foo
        logger.info("---> Target(Foo) called")
    }

    constructor(foo: Foo?, bar: Bar?) {
        fooOne = foo
        this.bar = bar
        logger.info("---> Target(Foo,Bar) called")
    }

    companion object {
        private val logger: org.slf4j.Logger =
            org.slf4j.LoggerFactory.getLogger(TrickyTarget::class.java)
    }
}

@Configuration
@ComponentScan
open class AutoWiringCfg {
    @Bean
    @Primary
    open fun fooImplOne(): Foo = FooImplOne()

    @Bean
    open fun fooImplTwo(): Foo = FooImplTwo()

    @Bean
    open fun bar(): Bar = Bar()

    @Bean
    open fun trickyTarget(): TrickyTarget = TrickyTarget()
}