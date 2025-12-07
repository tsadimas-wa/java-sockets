# Java Socket Programming Examples

A collection of Java socket programming examples demonstrating client-server communication patterns, including basic connections, persistent servers, and multi-threaded architectures.

## Overview

This repository contains educational examples for teaching socket programming concepts in Java. The examples progress from simple single-connection servers to more advanced multi-threaded server implementations. Each example is provided in two versions: one with hardcoded configuration and one that accepts command-line arguments for flexibility.

## Files Description

### Server Implementations (Fixed Port 5000)

- **`Server.java`** - Basic single-client server that accepts one connection, processes messages, and terminates after the client disconnects.

- **`ServerAlwaysRun.java`** - Persistent server that continues running after client disconnections, accepting new clients sequentially (one at a time).

- **`ServerMultiThread.java`** - Multi-threaded server that can handle multiple concurrent clients by spawning a new thread for each connection.

### Server Implementations (Configurable Port)

- **`ServerArgs.java`** - Basic single-client server with port number as command-line argument.

- **`ServerAlwaysRunArgs.java`** - Persistent server with configurable port via command-line argument.

- **`ServerMultiThreadArgs.java`** - Multi-threaded server with configurable port via command-line argument.

### Supporting Classes

- **`ClientHandler.java`** - Runnable class used by multi-threaded servers to handle individual client connections in separate threads.

### Client Implementations (Fixed Host/Port)

- **`Client.java`** - Basic client implementation using try-with-resources for automatic resource management (connects to localhost:5000).

- **`ClientClose.java`** - Client implementation demonstrating manual resource cleanup in the finally block (alternative to try-with-resources).

### Client Implementations (Configurable Host/Port)

- **`ClientArgs.java`** - Client with configurable host and port via command-line arguments, using try-with-resources.

- **`ClientCloseArgs.java`** - Client with configurable host and port, demonstrating manual resource cleanup.

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
javac ServerArgs.java ClientArgs.java
```

## Usage

### Example 1: Basic Server and Client (Fixed Port)

**Terminal 1 - Start the server:**
```bash
java Server
```

**Terminal 2 - Start the client:**
```bash
java Client
```

Type messages in the client terminal. The server will echo back with "ACK" prefix. Type `quit` to terminate the connection.

### Example 2: Basic Server and Client (Configurable Port)

**Terminal 1 - Start the server on a custom port:**
```bash
java ServerArgs 8080
```

**Terminal 2 - Start the client connecting to the custom port:**
```bash
java ClientArgs localhost 8080
```

This allows you to run multiple server instances on different ports simultaneously.

### Example 3: Persistent Server

**Terminal 1 - Start the persistent server (fixed port):**
```bash
java ServerAlwaysRun
```

**Or with custom port:**
```bash
java ServerAlwaysRunArgs 9000
```

**Terminal 2 - Start multiple clients sequentially:**
```bash
java Client
# Or with custom configuration:
java ClientArgs localhost 9000
```

The server will handle each client one after another, continuing to run after each client disconnects.

### Example 4: Multi-threaded Server

**Terminal 1 - Start the multi-threaded server (fixed port):**
```bash
java ServerMultiThread
```

**Or with custom port:**
```bash
java ServerMultiThreadArgs 7000
```

**Terminal 2, 3, 4... - Start multiple clients simultaneously:**
```bash
java Client
# Or with custom configuration:
java ClientArgs localhost 7000
```

The server can handle multiple clients concurrently, with each client managed in its own thread.

## Features Demonstrated

### Command-Line Arguments
- **Fixed configuration**: Simple hardcoded host/port for quick demonstrations
- **Configurable parameters**: `*Args.java` versions accept command-line arguments for flexibility
- **Input validation**: Proper error handling for invalid port numbers and missing arguments

### Resource Management
- **Try-with-resources**: Automatic resource cleanup (`Client.java`, `Server.java`, `ClientArgs.java`, `ServerArgs.java`)
- **Manual cleanup**: Explicit resource management in finally blocks (`ClientClose.java`, `ClientCloseArgs.java`)

### Server Patterns
- **Single connection**: Basic server that handles one client and exits
- **Sequential connections**: Server that handles clients one at a time in a loop
- **Concurrent connections**: Multi-threaded server handling multiple clients simultaneously

### Communication Protocol
- Simple text-based protocol
- Echo/acknowledgment pattern (ACK messages)
- Graceful shutdown with "quit" command

## Network Configuration

### Fixed Configuration Files
All non-Args examples use:
- **Host**: localhost (127.0.0.1)
- **Port**: 5000

### Configurable Files
The `*Args.java` versions accept:
- **Server**: `java ServerArgs <port>` or `java ServerAlwaysRunArgs <port>` or `java ServerMultiThreadArgs <port>`
- **Client**: `java ClientArgs <host> <port>` or `java ClientCloseArgs <host> <port>`

This allows testing on different ports and connecting to remote hosts.

## Learning Objectives

This repository helps students understand:
1. Basic socket programming in Java
2. Client-server architecture
3. Command-line argument parsing and validation
4. Resource management and cleanup strategies
5. Server lifecycle patterns
6. Multi-threading for concurrent client handling
7. I/O streams and buffered communication
8. Network configuration flexibility

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
