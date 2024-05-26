package com.apress.prospring6.decoupled

import java.util.*

object MessageSupportFactory {
    var renderer:MessageRenderer?=null
    var provider:MessageProvider?=null

    init {
        val props= Properties()
        try {
            props.load(this.javaClass.getResourceAsStream("/msf.properties"))
            val rendererClass=props.getProperty("renderer.class")
            val providerClass=props.getProperty("provider.class")
            renderer=Class.forName(rendererClass).getDeclaredConstructor().newInstance() as MessageRenderer
            provider=Class.forName(providerClass).getDeclaredConstructor().newInstance() as MessageProvider
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}