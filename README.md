# Java Socket Programming Examples

A collection of Java socket programming examples demonstrating client-server communication patterns, including basic connections, persistent servers, and multi-threaded architectures.

## Overview

This repository contains educational examples for teaching socket programming concepts in Java. The examples progress from simple single-connection servers to more advanced multi-threaded server implementations.

## Files Description

### Server Implementations

- **`Server.java`** - Basic single-client server that accepts one connection, processes messages, and terminates after the client disconnects.

- **`ServerAlwaysRun.java`** - Persistent server that continues running after client disconnections, accepting new clients sequentially (one at a time).

- **`ServerMultiThread.java`** - Multi-threaded server that can handle multiple concurrent clients by spawning a new thread for each connection.

- **`ClientHandler.java`** - Runnable class used by `ServerMultiThread` to handle individual client connections in separate threads.

### Client Implementations

- **`Client.java`** - Basic client implementation using try-with-resources for automatic resource management.

- **`ClientClose.java`** - Client implementation demonstrating manual resource cleanup in the finally block (alternative to try-with-resources).

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

## Compilation

Compile all Java files:

```bash
javac *.java
```

Or compile individual files as needed:

```bash
javac Server.java Client.java
```

## Usage

### Example 1: Basic Server and Client

**Terminal 1 - Start the server:**
```bash
java Server
```

**Terminal 2 - Start the client:**
```bash
java Client
```

Type messages in the client terminal. The server will echo back with "ACK" prefix. Type `quit` to terminate the connection.

### Example 2: Persistent Server

**Terminal 1 - Start the persistent server:**
```bash
java ServerAlwaysRun
```

**Terminal 2 - Start multiple clients sequentially:**
```bash
java Client
```

The server will handle each client one after another, continuing to run after each client disconnects.

### Example 3: Multi-threaded Server

**Terminal 1 - Start the multi-threaded server:**
```bash
java ServerMultiThread
```

**Terminal 2, 3, 4... - Start multiple clients simultaneously:**
```bash
java Client
```

The server can handle multiple clients concurrently, with each client managed in its own thread.

## Features Demonstrated

### Resource Management
- **Try-with-resources**: Automatic resource cleanup (`Client.java`, `Server.java`)
- **Manual cleanup**: Explicit resource management in finally blocks (`ClientClose.java`)

### Server Patterns
- **Single connection**: Basic server that handles one client and exits
- **Sequential connections**: Server that handles clients one at a time in a loop
- **Concurrent connections**: Multi-threaded server handling multiple clients simultaneously

### Communication Protocol
- Simple text-based protocol
- Echo/acknowledgment pattern (ACK messages)
- Graceful shutdown with "quit" command

## Network Configuration

All examples use:
- **Host**: localhost (127.0.0.1)
- **Port**: 5000

To change the port, modify the port number in the respective server and client files.

## Learning Objectives

This repository helps students understand:
1. Basic socket programming in Java
2. Client-server architecture
3. Resource management and cleanup strategies
4. Server lifecycle patterns
5. Multi-threading for concurrent client handling
6. I/O streams and buffered communication

## Notes

- The server must be started before the client attempts to connect
- Type `quit` in the client to gracefully close the connection
- In multi-threaded mode, each client connection is handled independently
- Greek text in `ClientHandler.java` and `ServerMultiThread.java` is used for educational purposes in a Greek-speaking classroom

## Error Handling

The examples include error handling for common scenarios:
- Unknown host exceptions
- I/O errors during communication
- Connection failures
- Resource cleanup errors

## License

Educational material for the University of West Attica (UNIWA).
