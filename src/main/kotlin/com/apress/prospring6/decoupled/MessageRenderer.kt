package com.apress.prospring6.decoupled

interface MessageRenderer {
    fun render()
    var messageProvider: MessageProvider?
}