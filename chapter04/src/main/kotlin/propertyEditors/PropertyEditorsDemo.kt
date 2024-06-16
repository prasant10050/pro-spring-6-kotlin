package com.apress.prospring6.propertyEditors

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component
import java.io.*
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors
import kotlin.jvm.Throws

object PropertyEditorsDemo {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val baseDir=File(System.getProperty("java.io.tmpdir"))
        val path= Files.createFile(Path.of(baseDir.absolutePath,"test.txt"))
        Files.writeString(path,"Hello world\nHello Universe")
        path.toFile().deleteOnExit()
        val ctx=AnnotationConfigApplicationContext()
        ctx.register(ValueHolder::class.java,DiverseValuesContainer::class.java)
        ctx.refresh()
        ctx.close()
    }
}

internal class ValueHolder(stringList: List<String?>?) {
    var stringList: List<String>
    var inputStream: InputStream? = null

    init {
        this.stringList = java.util.List.of("Mayer", "Psihoza", "Mazikeen")
        try {
            inputStream = FileInputStream(
                System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "test.txt"
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
}

@Component("builtInSimple")
class DiverseValuesContainer {
    private var bytes: ByteArray? = null
    private var character: Char? = null
    private var cls: Class<*>? = null
    private var trueOrFalse: Boolean? = null
    private var stringList: List<String>? = null
    private var date: Date? = null
    private var floatValue: Float? = null
    private var file: File? = null
    private var stream: InputStream? = null
    private var locale: Locale? = null
    private var pattern: Pattern? = null
    private var properties: Properties? = null
    private var trimString: String? = null
    private var url: URL? = null

    @Value("John Mayer")
    fun setBytes(vararg bytes: Byte) {
        logger.info("Setting bytes: {}", Arrays.toString(bytes))
        this.bytes = bytes
    }

    @Value("A")
    fun setCharacter(character: Char) {
        logger.info("Setting character: {}", character)
        this.character = character
    }


    @Value("java.lang.String")
    fun setCls(cls: Class<*>) {
        logger.info("Setting class: {}", cls.name)
        this.cls = cls
    }

    @Value("true")
    fun setTrueOrFalse(trueOrFalse: Boolean?) {
        logger.info("Setting Boolean: {}", trueOrFalse)
        this.trueOrFalse = trueOrFalse
    }

    @Value("#{valueHolder.stringList}")
    fun setStringList(stringList: List<String>?) {
        logger.info("Setting stringList with: {}", stringList)
        this.stringList = stringList
    }

    @Value("20/10/1988")
    fun setDate(date: Date?) {
        logger.info("Setting date: {}", date)
        this.date = date
    }

    @Value("123.45678")
    fun setFloatValue(floatValue: Float?) {
        logger.info("Setting float value: {}", floatValue)
        this.floatValue = floatValue
    }

    @Value("#{systemProperties['java.io.tmpdir']}#{systemProperties['file.separator']}test.txt")
    fun setFile(file: File) {
        logger.info("Setting file: {}", file.absolutePath)
        this.file = file
    }

    @Value("#{valueHolder.inputStream}")
    fun setStream(stream: InputStream?) {
        this.stream = stream
        logger.info(
            "Setting stream & reading from it: {}",
            BufferedReader(InputStreamReader(stream)).lines().parallel().collect(Collectors.joining("\n"))
        )
    }

    @Value("en_US")
    fun setLocale(locale: Locale) {
        logger.info("Setting locale: {}", locale.displayName)
        this.locale = locale
    }

    @Value("a*b")
    fun setPatternValue(pattern: Pattern?) {
        logger.info("Setting pattern: {}", pattern)
        this.pattern = pattern
    }

    @Value("   String need trimming   ")
    fun setTrimString(trimString: String?) {
        logger.info("Setting trim string: {}", trimString)
        this.trimString = trimString
    }

    @Value("name=Ben age=41")
    fun setProperties(properties: Properties) {
        logger.info("Loaded {}", properties.size.toString() + " properties")
        this.properties = properties
    }

    @Value("https://iuliana-cosmina.com")
    fun setUrl(url: URL) {
        logger.info("Setting URL: {}", url.toExternalForm())
        this.url = url
    }

    companion object {
        private val logger: Logger =
            LoggerFactory.getLogger(DiverseValuesContainer::class.java)
    }
}