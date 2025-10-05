package com.atsushi.kitazawa

interface Terminal {
    var macAddress: String
//    fun send(toMacAddress: String, msg: String)
    fun receive(fromMacAddress: String, toMacAddress: String, msg: String): Boolean
    fun matchMacAddress(macAddress: String): Boolean
    fun connectSwitch(s: Switch)
    fun detachSwitch()
    fun isConnected(): Boolean
}