package com.atsushi.kitazawa

fun main() {
    val emulator = Emulator()
    emulator.doExec()
}

class Emulator {
    private val switch = Switch()
    fun doExec() {
        while (true) {
            val input = readln()
            when {
                input.startsWith("connect") -> {
                    val parts = input.split(" ")
                    val port = parts.getOrNull(1)?.toIntOrNull() ?: continue
                    val terminal = Terminal(MacAddressGenerator.generate())
                    connect(terminal, switch, port)
                    switch.printMacAddressTable()
                }
                input.startsWith("detach") -> {
                    val parts = input.split(" ")
                    val port = parts.getOrNull(1)?.toIntOrNull() ?: continue
                    detach(switch, port)
                    switch.printMacAddressTable()
                }
                input.startsWith("send") -> {}
                (input == "exit") -> break
                else -> {
                    println(input)
                }
            }
        }
    }

    // スイッチと端末の初期化を実施
    private fun initSwitch() {
        val switch = Switch()
        val terminal1 = Terminal("7A:88:A4:0F:C5:D1")
        val terminal2 = Terminal("1A:DC:A4:56:0F:14")
        val terminal3 = Terminal("48:99:B3:DA:1D:E9")

        // 端末1の接続
        connect(terminal1, switch, 1)
        // 端末2の接続
        connect(terminal2, switch, 2)
        // 端末3の接続
        connect(terminal3, switch, 3)
        // アドレステーブルの表示
        switch.printMacAddressTable()

        // メッセージ送信（端末1->端末2）
        terminal1.send("1A:DC:A4:56:0F:14", "hello!!")

        // 利用中ポートへの接続（接続できない）
        val terminal4 = Terminal(MacAddressGenerator.generate())
        connect(terminal4, switch, 1)

        // 端末1の切断
//        detach(terminal1, switch)
//        switch.printMacAddressTable()
    }

    private fun connect(terminal: Terminal, switch: Switch, port: Int) {
        switch.connectTerminal(port, terminal)
        terminal.connectSwitch(switch)
    }

    private fun detach(switch: Switch, port: Int) {
        val terminal = switch.detachTerminal(port)
        terminal?.detachSwitch()
    }
}