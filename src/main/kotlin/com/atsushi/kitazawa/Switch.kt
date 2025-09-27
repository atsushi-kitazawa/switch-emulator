package com.atsushi.kitazawa

class Switch {
    private val ports = listOf(1, 2, 3)
    private val table = MacAddressTable(ports)

    // TODO
    // スイッチは端末を知っていてよいか？
    // MACアドレスだけな気がするがそうするとメッセージが送信できない
    //
    // 知っていてもいい気がする
    // 接続されている端末は知っている
    // メッセージの送信側は受信側のMACアドレスを指定して送信する
    //
    // 端末が端末を知っているのはおかしい
    fun connectTerminal(port: Int, terminal: Terminal): Boolean {
        // ポートが利用中の場合、接続しない
        if (table.isPortUsed(port)) {
            println("port $port is used. cannot connect.")
            return false
        }

        table.updatePort(port, terminal)
        return true
    }

    fun detachTerminal(terminal: Terminal): Boolean {
        val port = table.findPortFromMacAddress(terminal.macAddress) ?: return false
        table.updatePort(port, null)
        return true
    }

    fun proxy(fromMacAddress: String, toMacAddress: String, msg: String) {
        // TODO ポートを特定できない場合、すべてのポートに送信する
        val port = table.findPortFromMacAddress(toMacAddress) ?: return
        val terminal = table.getTerminalFromPort(port)
        terminal?.receive(fromMacAddress, toMacAddress, msg)
    }

    fun printMacAddressTable() {
        println("===== print mac address table =====")
        println(table)
    }

    /**
     * MACアドレステーブルを表現するクラス
     */
    class MacAddressTable(ports: List<Int>) {
        private val table = mutableMapOf<Int, Terminal?>()

        init {
            for (p in ports) {
                table[p] = null
            }
        }

        fun updatePort(port: Int, terminal: Terminal?) {
            if (port in table) {
                table[port] = terminal
            }
        }

        // TODO ポートが特定できない可能性があることを考慮する
        fun findPortFromMacAddress(macAddress: String): Int? {
            val filterMap = table.filterKeys { key ->
                table[key]?.matchMacAddress(macAddress) ?: false
            }
            return filterMap.keys.firstOrNull()
        }

        fun getTerminalFromPort(port: Int): Terminal? {
            return table[port]
        }

        fun isPortUsed(port: Int): Boolean {
            return table[port] != null
        }

        override fun toString(): String {
            return table.toString()
        }
    }
}