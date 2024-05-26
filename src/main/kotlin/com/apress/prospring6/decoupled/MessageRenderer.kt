package com.apress.prospring6.decoupled

import com.apress.prospring6.three.ManagedComponent


interface MessageRenderer {
    fun render()
    var messageProvider: MessageProvider?
}