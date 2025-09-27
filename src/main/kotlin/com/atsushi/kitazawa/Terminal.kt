package com.atsushi.kitazawa

class Terminal(var macAddress: String) {
    private var switch: Switch? = null

    fun send(toMacAddress: String, msg: String) {
        switch?.proxy(macAddress, toMacAddress, msg)
    }

    // toMacAddressが自身のMACアドレスと一致するか確認
    // 一致した場合、自身宛のメッセージとして処理する
    // 一致しなかった場合、自身宛ではないとしてメッセージを破棄する
    fun receive(fromMacAddress: String, toMacAddress: String, msg: String): Boolean {
        if (toMacAddress != this.macAddress) return false

        println("from $fromMacAddress message receive '$msg'")
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
        return "Terminal(macAddress=$macAddress)"
    }
}