# Reflection Questions

## Instructions
Answer the following questions about your learning experience. Each answer should be **at least 5-7 sentences** and show your understanding.

---

## Question 1: What did you learn about multithreading?

**Your Answer:**

Through this assignment, I learned the fundamentals of multithreading and how threads are created and managed in Java. I understood how each thread runs independently but shares the same memory space within a process. I also learned about different thread states such as New, Runnable, Running, Waiting, and Terminated, and how a thread moves between these states during execution. One important concept I learned is how threads can simulate concurrent execution, even if the CPU is switching between them quickly.

I also found it interesting how methods like Thread.sleep() can change the state of a thread and simulate real processing time. Another key takeaway was understanding how scheduling works, especially with Round-Robin, where each thread gets a fair share of CPU time. This helped me see how operating systems manage multiple tasks efficiently.

---

## Question 2: What was the most challenging part of this assignment?

**Your Answer:**

The most challenging part of this assignment was implementing the waiting time tracking feature. It was difficult to determine exactly when to start and stop measuring the waiting time for each process. Since processes are repeatedly added back to the ready queue, I had to carefully track when a process begins waiting and when it resumes execution.

Another challenge was understanding where to place the waiting time calculation in the code without affecting the original Round-Robin scheduling logic. I needed to make sure that my implementation did not break the flow of the program. This required a good understanding of how the scheduling loop works and how threads move between different states.

Overall, this part was challenging because it required both logical thinking and a clear understanding of how time and scheduling work in multithreading.

---

## Question 3: How did you overcome the challenges you faced?

**Your Answer:**

To overcome these challenges, I focused on understanding the flow of the program step by step. I carefully read through the code and identified where each process is added to the queue and when it starts execution. I used simple debugging techniques like printing values and testing the program multiple times to verify my logic.
I also reviewed lecture materials and examples related to threads and scheduling to better understand the concepts. Breaking the problem into smaller parts helped me focus on one feature at a time instead of getting overwhelmed. Over time, this approach made it easier to implement and test each feature successfully.

---

## Question 4: How can you apply multithreading concepts in real-world applications?

**Your Answer:**

Multithreading is widely used in many real-world applications to improve performance and responsiveness. For example, web browsers use multiple threads to load web pages, handle user input, and run background tasks at the same time. This ensures that the browser remains responsive even when loading complex websites.

Another example is mobile applications, where threads are used to perform background operations like downloading data or processing images while keeping the user interface smooth. In operating systems, multithreading allows multiple programs to run simultaneously without freezing the system. This assignment helped me understand how these systems manage tasks efficiently using scheduling techniques like Round-Robin.

---

## Additional Reflections (Optional)

### What would you like to learn more about?

I would like to learn more about advanced topics in multithreading such as thread synchronization, race conditions, and how to avoid deadlocks. These concepts seem important for building reliable and efficient concurrent programs.

---

### How confident do you feel about multithreading concepts now?

Intermediate 

I feel more confident than before because I now understand how threads work, how they are scheduled, and how to implement basic multithreading features. However, I still need more practice with advanced concepts like synchronization and handling shared resources safely.

---

### Feedback on the assignment

This assignment was very helpful in understanding how multithreading works in practice. It allowed me to apply theoretical concepts in a real coding scenario. The features we implemented made the project more interesting and gave a better understanding of scheduling. The assignment was a bit challenging, but it was useful for learning and improving problem-solving skills.
