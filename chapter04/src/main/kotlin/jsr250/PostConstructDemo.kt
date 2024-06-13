package com.apress.prospring6.jsr250

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanCreationException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

object PostConstructDemo {
    private val logger: Logger =
        LoggerFactory.getLogger(PostConstructDemo::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val ctx =
            AnnotationConfigApplicationContext(SingerConfiguration::class.java)
        getBean("singerOne", ctx)
        getBean("singerTwo", ctx)
        getBean("singerThree", ctx)
        //ctx.close()
        ctx.registerShutdownHook()
    }

    fun getBean(beanName: String?, ctx: ApplicationContext): Singer? {
        return try {
            val bean = beanName?.let { ctx.getBean(it) } as Singer
            logger.info("Found: {}", bean)
            bean
        } catch (ex: BeanCreationException) {
            logger.error("An error occured in bean configuration: " + ex.message)
            null
        }
    }
}

class Singer {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Singer::class.java)
        private const val DEFAULT_NAME = "No Name"
        private const val DEFAULT_AGE = 0
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
//        if (age == Int.MIN_VALUE) {
//            logger.info("using default age")
//            age = DEFAULT_AGE
//        }
        require(age != Int.MIN_VALUE) { "You must set the age property of any beans of type" + Singer::class.java }
    }

    override fun toString(): String {
        return "name=${name}, age=${name}"
    }

    @PostConstruct
    @Throws(Exception::class)
    private fun postConstruct() {
        logger.info("Initializing bean using 'postConstruct()'")
        init()
    }

    @PreDestroy
    @Throws(Exception::class)
    private fun preDestroy() {
        logger.info("Calling preDestroy() on bean")
    }

}

@Configuration
open class SingerConfiguration {
    @Bean
    open fun singerOne(): Singer {
        val singer = Singer()
        singer.name = "John Mayer"
        singer.age = 43
        return singer
    }

    @Bean
    open fun singerTwo(): Singer {
        val singer = Singer()
        singer.age = 42
        return singer
    }

    @Bean
    open fun singerThree(): Singer {
        val singer = Singer()
        singer.name = "John Butler"
        //singer.age=41
        return singer
    }
}


// : DisposableBean interface
//@Throws(Exception::class)
//override fun destroy() {
//LOGGER.info("Calling destroy() on bean of type {}", FileManager::class.java)
//Files.deleteIfExists(file)
//}

// @Bean(destroyMethod = "destroyMethod")
//@Throws(IOException::class)
//private fun destroyMethod() {
//    logger.info(
//    "Calling destroyMethod() on bean of type {}",
//    FileManager::class.java
//    )
//    Files.deleteIfExists(file)
//    }