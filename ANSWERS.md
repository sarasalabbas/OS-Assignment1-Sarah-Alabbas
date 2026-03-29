# Assignment Questions

## Instructions
Answer all 4 questions with detailed explanations. Each answer should be **3-5 sentences minimum** and demonstrate your understanding of the concepts.

---

## Question 1: Thread vs Process

**Question**: Explain the difference between a **thread** and a **process**. Why did we use threads in this assignment instead of creating separate processes?

**Your Answer:**

[A process is a program in execution that has its own memory space and system resources. Each process runs independently and does not share memory with other processes. A thread, on the other hand, is a smaller unit of execution within a process and shares the same memory with other threads.

Processes are heavier and require more resources, while threads are lightweight and faster to create. Communication between threads is easier because they share memory, whereas processes need more complex communication mechanisms.

We used threads in this assignment because they are more efficient for simulating CPU scheduling. Threads allow faster execution, lower overhead, and easier management compared to creating separate processes.]

---

## Question 2: Ready Queue Behavior

**Question**: In Round-Robin scheduling, what happens when a process doesn't finish within its time quantum? Explain using an example from your program output.

**Your Answer:**

[In Round-Robin scheduling, if a process does not finish within its time quantum, it is paused and moved to the end of the ready queue. This allows other processes to get CPU time, ensuring fairness among all processes.

The process will wait in the queue until its turn comes again, then it resumes execution for another time quantum. This continues until the process finishes completely.]

Example from my output:
```
[Context Switch #1
P1 executing quantum [4000ms] (Priority: 5)
Quantum progress: [███████████████] 100%
P1 completed quantum 4000ms │ Overall progress: [█████████░░░░░░░░░░░] 45%
Remaining time: 4830ms
P1 yields CPU for context switch

P1 added to ready queue │ Burst time: 8830ms │ Priority: 5

Ready Queue:
[P3(P:2) → P4(P:4) → ... → P13(P:3) → P1(P:5)]]
```

**Explanation of example:**
[This example shows that process P1 did not finish during its time quantum, so it stopped execution and yielded the CPU. After that, it was added back to the ready queue at the end. This ensures that other processes get a chance to run before P1 executes again, which is the main idea of Round-Robin scheduling.]

---

## Question 3: Thread States

**Question**: A thread can be in different states: **New**, **Runnable**, **Running**, **Waiting**, **Terminated**. Walk through these states for one process (P1) from your simulation.

**Your Answer:**

[A thread goes through several states during its lifecycle. In this simulation, process P1 transitions through all the thread states based on how it is scheduled and executed.]

1. **New**: [P1 is in the New state when the thread is created using new Thread(process) but before calling start().]

2. **Runnable**: [P1 becomes Runnable after calling start(), meaning it is ready to run and waiting for CPU scheduling.]

3. **Running**: [P1 becomes Runnable after calling start(), meaning it is ready to run and waiting for CPU scheduling.]

4. **Waiting**: [P1 enters the Waiting state when Thread.sleep() is used to simulate execution time during the quantum.]

5. **Terminated**: [P1 reaches the Terminated state after finishing execution when its remaining time becomes zero.]

---

## Question 4: Real-World Applications

**Question**: Give **TWO** real-world examples where Round-Robin scheduling with threads would be useful. Explain why this scheduling algorithm works well for those scenarios.

**Your Answer:**

### Example 1: [Web Server Request Handling]

**Description**: 
[A web server handles multiple user requests at the same time, such as loading pages or processing data.]

**Why Round-Robin works well here**: 
[Round-Robin ensures that each request gets equal CPU time, improving responsiveness. It prevents one request from blocking others and keeps the system fair for all users.]

### Example 2: [Operating System Task Scheduling]

**Description**: 
[An operating system manages multiple running applications like browsers, editors, and background processes.]

**Why Round-Robin works well here**: 
[Round-Robin provides fairness by giving each process a fixed time slice. This prevents starvation and ensures all programs run smoothly and consistently.]

---

## Summary

**Key concepts I understood through these questions:**
1. The difference between threads and processes
2. How Round-Robin scheduling works
3. The lifecycle of a thread

**Concepts I need to study more:**
1. Advanced thread synchronization
2. Comparison between different CPU scheduling algorithms
