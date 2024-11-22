# Real-Time Ticket Booking System

A Java-based ticket booking simulation that uses the Producer-Consumer model. Vendors add tickets to the system, and customers purchase tickets in real-time while maintaining thread safety.

## Features

- **Dynamic Initialization:** Users can set key parameters like ticket capacity, ticket release rate, and customer retrieval rate at runtime.

- **Thread Synchronization:** Uses thread-safe methods to handle concurrent operations.

- **Real-Time Simulation:**

  - Vendors add tickets at a fixed interval.
  - Customers retrieve tickets concurrently at their own interval.

- **Graceful Exit:** Stop the simulation before it starts by confirming your inputs.

- **Input Validation:** Ensures valid inputs for ticket capacity and rates to prevent errors.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code (optional).
- Basic knowledge of Java and multi-threading concepts.

### Setup

1. Clone the repository:

```bash
https://github.com/kasMadu/QBook.git
```

2. Navigate to the project directory:

```bash
cd CLI
cd cli_simulator
```

3. Open the project in your preferred IDE or use a terminal for compilation and execution.

## Running the Application

### Steps to Run

1. Compile and run the program:

```bash
java QBOOK/CLI/cli_simulator/src/main/java/org/qbook_cli/Main.java
```

2. Enter the required parameters when prompted:

- Maximum ticket capacity: Total tickets allowed in the system (must be > 0).
- Ticket release rate: Frequency of adding tickets (must be > 0).
- Customer retrieval rate: Frequency of purchasing tickets (must be > 0).

Example input:

```java
Welcome to the QBOOK!
Enter maximum ticket capacity (must be > 0): 10
Enter ticket release rate (must be > 0): 3
Enter customer retrieval rate (must be > 0): 2
```

3. Confirm to start the simulation:

```java
Simulation Parameters:
Max Ticket Capacity: 10
Ticket Release Rate: 3
Customer Retrieval Rate: 2
Do you want to start the simulation? (yes/no): yes
```

- If you type yes, the simulation begins.
- If you type no, the program exits gracefully:
  Copy code

```bash
Simulation canceled. Exiting program.
```

4. Observe real-time operations in the console, such as:

```bash
Vendor added 3 tickets. Total tickets: 3
Customer purchased 2 tickets. Remaining tickets: 1
Vendor added 3 tickets. Total tickets: 4
Customer purchased 1 ticket. Remaining tickets: 3
```

## Validation Rules

The system ensures all inputs are valid and prevents runtime errors by enforcing the following rules:

1. **Maximum Ticket Capacity:**

   - Must be a positive integer greater than 0.

2. **Ticket Release Rate:**

   - Must be a positive integer greater than 0.

3. **Customer Retrieval Rate:**
   - Must be a positive integer greater than 0.

Example of invalid input handling:

```bash
Enter maximum ticket capacity (must be > 0): -5
Error: Value must be greater than 0. Please try again.
Enter maximum ticket capacity (must be > 0): abc
Error: Please enter a valid integer.
Enter maximum ticket capacity (must be > 0): 10
```

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/kasMadu/QBook/blob/dfbb19df2643036777527f819b685434f695d5a9/LICENSE/) file for more details.

## Acknowledgments

Inspired by the classic Producer-Consumer problem in computer science.
Thank you to all contributors and open-source libraries for support!
