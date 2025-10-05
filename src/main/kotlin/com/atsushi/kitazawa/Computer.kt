package com.atsushi.kitazawa

class Computer(var macAddress: String) {
    private var switch: Switch? = null

    fun send(toMacAddress: String, msg: String) {
        switch?.proxy(macAddress, toMacAddress, msg)
    }

    fun receive(fromMacAddress: String, toMacAddress: String, msg: String): Boolean {
        // 自身宛のメッセージ以外は破棄する
        if (toMacAddress != this.macAddress) return false
        println("($toMacAddress) from $fromMacAddress message receive '$msg'")
        return true
    }

    fun matchMacAddress(macAddress: String): Boolean {
        return macAddress == this.macAddress
    }

    fun connectSwitch(s: Switch) {
        this.switch = s
    }

    fun detachSwitch() {
        switch = null
    }

    override fun toString(): String {
        return "Computer(macAddress=$macAddress)"
    }
}