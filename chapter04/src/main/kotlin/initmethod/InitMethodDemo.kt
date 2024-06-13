package com.apress.prospring6.initmethod

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanCreationException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

object InitMethodDemo {
    private val logger: Logger = LoggerFactory.getLogger(InitMethodDemo::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val ctx=AnnotationConfigApplicationContext(SingerConfiguration::class.java)
        getBean("singerOne",ctx)
        getBean("singerTwo",ctx)
        getBean("singerThree",ctx)
    }

    fun getBean(beanName:String?,ctx:ApplicationContext):Singer?{
        return try {
            val bean= beanName?.let { ctx.getBean(it) } as Singer
            logger.info("Found: {}",bean)
            bean
        }catch (ex:BeanCreationException){
            logger.error("An error occured in bean configuration: " + ex.message)
            null
        }
    }
}

class Singer {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Singer::class.java)
        private const val DEFAULT_NAME = "No Name"
    }

    var name: String? = null
        set(value) {
            logger.info(
                "Calling setName for bean of type {}.",
                Singer::class.java
            )
            field = value
        }
    var age: Int = Int.MIN_VALUE
        set(value) {
            logger.info(
                "Calling setAge for bean of type {}.",
                Singer::class.java
            )
            field = value
        }

    private fun init() {
        logger.info("Initializing bean")
        if (name == null) {
            logger.info("using default name")
            name = DEFAULT_NAME
        }
        require(age != Int.MIN_VALUE) { "You must set the age property of any beans of type" + Singer::class.java }
    }

    override fun toString(): String {
        return "name=${name}, age=${name}"
    }
}

@Configuration
open class SingerConfiguration{
    @Bean(initMethod = "init")
    open fun singerOne():Singer{
        val singer=Singer()
        singer.name="John Mayer"
        singer.age=43
        return singer
    }

    @Bean(initMethod = "init")
    open fun singerTwo():Singer{
        val singer=Singer()
        singer.age=42
        return singer
    }

    @Bean(initMethod = "init")
    open fun singerThree():Singer{
        val singer=Singer()
        singer.name="John Butler"
        return singer
    }
}