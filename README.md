# Simple Switching Hub Emulator 💻

This repository contains a simple implementation of a **Switching Hub Emulator**.

-----

## 🚀 Getting Started

The emulator is started by providing the number of ports for the switching hub as a command-line argument.

### ⚙️ How to Run

Execute the program with the desired number of ports:

```kotlin
xxx 3
```

After startup, the content of the **address table** will be displayed, and the emulator will wait for your input.

### 🛑 How to Stop

To stop the emulator, type `exit` and press Enter:

```
exit
```

-----

## 💡 Usage

Here are the available commands to interact with the emulator:

### 🔗 Connecting a Device

Connect a device to a specific port:

```
connect <port number>
```

*Example:* `connect 1`

### ❌ Disconnecting a Device

Disconnect a device from a specific port:

```
detach <port number>
```

*Example:* `detach 1`

### ✉️ Sending a Message

Send a message from one port to another:

```
send <from mac address> <to mac address> <msg>
```

*Example:* `send 1 2 Hello_World`