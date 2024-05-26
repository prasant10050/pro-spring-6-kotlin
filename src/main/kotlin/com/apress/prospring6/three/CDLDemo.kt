package com.apress.prospring6.three

interface ManagedComponent{
    fun performLookup(container:Container)
}

interface Container{
    fun getDependency(key:String):Any?
}