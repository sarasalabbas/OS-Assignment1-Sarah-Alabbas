import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

// ANSI Color Codes for enhanced terminal output
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

// Class representing a process that implements Runnable to be run by a thread
class Process implements Runnable {
    private String name; // Name of the process
    private int burstTime; // Total time the process requires to complete (in milliseconds)
    private int timeQuantum; // Time slice (time quantum) allowed per CPU access (in milliseconds)
    private int remainingTime; // Time left for the process to finish its execution

    // Feature 1: Add process priority (1-5, where 5 is highest)
    private int priority;

    // Feature 3: Fields for waiting time tracking
    private long creationTime;      // time when process was created
    private long lastEnqueueTime;   // last time process entered ready queue
    private long waitingTime;       // total waiting time in milliseconds

    // Constructor to initialize the process with name, burst time, time quantum, and priority
    public Process(String name, int burstTime, int timeQuantum, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime;
        this.priority = priority;

        // Feature 3: initialize waiting time tracking
        this.creationTime = System.currentTimeMillis();
        this.lastEnqueueTime = creationTime;
        this.waitingTime = 0;
    }

    // This method will be called when the thread for this process is started
    @Override
    public void run() {
        // Feature 3: add how long the process waited in ready queue before getting CPU
        long currentTime = System.currentTimeMillis();
        waitingTime += (currentTime - lastEnqueueTime);

        // Simulate running for either the time quantum or remaining time, whichever is smaller
        int runTime = Math.min(timeQuantum, remainingTime);

        // Show quantum execution starting
        String quantumBar = createProgressBar(0, 15);
        System.out.println(Colors.BRIGHT_GREEN + "  ▶ " + Colors.BOLD + Colors.CYAN + name +
                          Colors.RESET + Colors.GREEN + " executing quantum" + Colors.RESET +
                          " [" + runTime + "ms] " +
                          Colors.MAGENTA + "(Priority: " + priority + ")" + Colors.RESET);

        try {
            // Simulate quantum execution with progress updates
            int steps = 5;
            int stepTime = runTime / steps;

            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);
                int quantumProgress = (i * 100) / steps;
                quantumBar = createProgressBar(quantumProgress, 15);

                // Clear line and show updated progress
                System.out.print("\r  " + Colors.YELLOW + "⚡" + Colors.RESET +
                                " Quantum progress: " + quantumBar);
            }
            System.out.println();

        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "\n  ✗ " + name + " was interrupted." + Colors.RESET);
        }

        remainingTime -= runTime;
        int overallProgress = (int) (((double)(burstTime - remainingTime) / burstTime) * 100);
        String overallProgressBar = createProgressBar(overallProgress, 20);

        System.out.println(Colors.YELLOW + "  ⏸ " + Colors.CYAN + name + Colors.RESET +
                          " completed quantum " + Colors.BRIGHT_YELLOW + runTime + "ms" + Colors.RESET +
                          " │ Overall progress: " + overallProgressBar);
        System.out.println(Colors.MAGENTA + "     Remaining time: " + remainingTime + "ms" + Colors.RESET);

        // If the process still has remaining time, it yields CPU for the next process
        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET +
                              " yields CPU for context switch" + Colors.RESET);
        } else {
            // If no time is left, the process has finished its execution
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" +
                              Colors.RESET);
        }
        System.out.println();
    }

    // Helper method to create a visual progress bar
    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < filled) {
                bar.append(Colors.GREEN + "█" + Colors.RESET);
            } else {
                bar.append(Colors.WHITE + "░" + Colors.RESET);
            }
        }
        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    // Method to run the last process to completion, ignoring the time quantum
    public void runToCompletion() {
        try {
            // Feature 3: count waiting time before last process starts final execution
            long currentTime = System.currentTimeMillis();
            waitingTime += (currentTime - lastEnqueueTime);

            // Run for the remaining time without splitting into smaller time slices
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_CYAN + " is the last process, running to completion" +
                              Colors.RESET + " [" + remainingTime + "ms] " +
                              Colors.MAGENTA + "(Priority: " + priority + ")" + Colors.RESET);
            Thread.sleep(remainingTime);
            remainingTime = 0;
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" + Colors.RESET);
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "  ✗ " + name + " was interrupted." + Colors.RESET);
        }
    }

    // Feature 3: called every time the process is added to the ready queue
    public void markEnqueued() {
        lastEnqueueTime = System.currentTimeMillis();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getPriority() {
        return priority;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    // Check if the process has finished (i.e., no remaining time)
    public boolean isFinished() {
        return remainingTime <= 0;
    }
}

public class SchedulerSimulation {

    // Feature 2: Static counter for total context switches
    private static int contextSwitchCount = 0;

    public static void main(String[] args) {
        // ⚠️ IMPORTANT: Put your student ID here to seed the random number generator
        // This makes your output unique to you - DO NOT forget to change this!
        int studentID = 444052644;  // ← CHANGE THIS TO YOUR ACTUAL STUDENT ID

        Random random = new Random(studentID);

        // Feature 3: store all processes for final waiting-time summary
        List<Process> allProcesses = new ArrayList<>();

        // Define the time quantum in milliseconds (the maximum time a process gets in one round)
        int timeQuantum = 2000 + random.nextInt(4) * 1000; // Random: 2000, 3000, 4000, or 5000

        // Generate random number of processes between 10 and 20
        int numProcesses = 10 + random.nextInt(11); // Random number between 10 and 20

        // Queue to manage processes in a First-In-First-Out (FIFO) order
        Queue<Thread> processQueue = new LinkedList<>();

        // Map to associate each thread with its respective process object
        Map<Thread, Process> processMap = new HashMap<>();

        // Print simulation header with elegant formatting
        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╔═══════════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                          Colors.BG_BLUE + Colors.BRIGHT_WHITE + Colors.BOLD +
                          "                          CPU SCHEDULER SIMULATION                                " +
                          Colors.RESET + Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╠═══════════════════════════════════════════════════════════════════════════════════════╣" +
                          Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                          Colors.YELLOW + "  ⚙ Processes:     " + Colors.RESET + Colors.BRIGHT_YELLOW +
                          String.format("%-65s", numProcesses) +
                          Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                          Colors.YELLOW + "  ⏱ Time Quantum:  " + Colors.RESET + Colors.BRIGHT_YELLOW +
                          String.format("%-65s", timeQuantum + "ms") +
                          Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                          Colors.YELLOW + "  🔑 Student ID:    " + Colors.RESET + Colors.BRIGHT_YELLOW +
                          String.format("%-65s", studentID) +
                          Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╚═══════════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET + "\n");

        // Create 'numProcesses' number of processes
        for (int i = 1; i <= numProcesses; i++) {
            // Random burst time for each process between timeQuantum/2 and 3*timeQuantum
            int burstTime = timeQuantum / 2 + random.nextInt(2 * timeQuantum + 1);

            // Feature 1: Generate random priority between 1 and 5
            int priority = 1 + random.nextInt(5);

            // Create a new process object with a unique name, burst time, time quantum, and priority
            Process process = new Process("P" + i, burstTime, timeQuantum, priority);

            // Feature 3: keep reference for final summary table
            allProcesses.add(process);

            // Add the process to the ready queue and the map
            addProcessToQueue(process, processQueue, processMap);
        }

        // Start of the scheduler simulation
        System.out.println(Colors.BOLD + Colors.GREEN +
                          "╔════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN + "║" + Colors.RESET +
                          Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                          "                        ▶  SCHEDULER STARTING  ◀                               " +
                          Colors.RESET + Colors.BOLD + Colors.GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN +
                          "╚════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET + "\n");

        // Loop to manage the scheduling of processes
        while (!processQueue.isEmpty()) {
            // Get the next thread from the queue (FIFO)
            Thread currentThread = processQueue.poll();

            // Print the current process queue (list of process IDs in the queue)
            System.out.println(Colors.BOLD + Colors.MAGENTA + "┌─ Ready Queue " + "─".repeat(65) + Colors.RESET);
            System.out.print(Colors.MAGENTA + "│ " + Colors.RESET + Colors.BRIGHT_WHITE + "[" + Colors.RESET);
            int queueCount = 0;
            for (Thread thread : processQueue) {
                Process process = processMap.get(thread);
                if (queueCount > 0) System.out.print(Colors.WHITE + " → " + Colors.RESET);
                System.out.print(Colors.BRIGHT_CYAN + process.getName() + Colors.RESET +
                                 Colors.YELLOW + "(P:" + process.getPriority() + ")" + Colors.RESET);
                queueCount++;
            }
            if (queueCount == 0) {
                System.out.print(Colors.YELLOW + "empty" + Colors.RESET);
            }
            System.out.println(Colors.BRIGHT_WHITE + "]" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.MAGENTA + "└" + "─".repeat(79) + Colors.RESET + "\n");

            // Feature 2: Count a context switch every time a new process starts running
            contextSwitchCount++;
            System.out.println(Colors.BRIGHT_YELLOW + "  ⇄ Context Switch #" + contextSwitchCount + Colors.RESET);

            // Start the thread, which will run the process for one time quantum
            currentThread.start();

            try {
                // Wait for the thread to finish its time quantum before continuing to the next process
                currentThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }

            // Retrieve the process associated with the thread from the map
            Process process = processMap.get(currentThread);

            // Check if the process is not finished
            if (!process.isFinished()) {
                // If the process still has remaining time, check if there are more processes in queue
                if (!processQueue.isEmpty()) {
                    // Re-enqueue the process to give it another chance to run in the next round
                    addProcessToQueue(process, processQueue, processMap);
                } else {
                    // If this is the last process in the queue, run it to completion
                    System.out.println(Colors.BRIGHT_YELLOW + "  ⚠ " + Colors.CYAN + process.getName() +
                                      Colors.RESET + Colors.YELLOW + " is the last process → running to completion" +
                                      Colors.RESET);
                    process.runToCompletion();
                }
            }
        }

        // End of the scheduler simulation
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                          "╔════════════════════════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET +
                          Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                          "                     ✓  ALL PROCESSES COMPLETED  ✓                            " +
                          Colors.RESET + Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                          "╚════════════════════════════════════════════════════════════════════════════════╝" +
                          Colors.RESET + "\n");

        // Feature 2: Display total number of context switches at the end
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "Total context switches: " + Colors.BRIGHT_YELLOW + contextSwitchCount +
                          Colors.RESET + "\n");

        // Feature 3: Display waiting time summary table
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╔══════════════════════════════════════════════════════════════╗" +
                          Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                          Colors.BG_BLUE + Colors.BRIGHT_WHITE + Colors.BOLD +
                          "                 PROCESS WAITING TIME SUMMARY                 " +
                          Colors.RESET + Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╠══════════════════════════════════════════════════════════════╣" +
                          Colors.RESET);

        System.out.printf(Colors.BOLD + Colors.BRIGHT_CYAN + "║ %-12s %-15s %-24s ║%n" + Colors.RESET,
                "Process", "Burst Time", "Waiting Time");

        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╠══════════════════════════════════════════════════════════════╣" +
                          Colors.RESET);

        for (Process process : allProcesses) {
            System.out.printf(Colors.BRIGHT_CYAN + "║ " + Colors.RESET +
                            Colors.YELLOW + "%-12s " + Colors.RESET +
                            Colors.GREEN + "%-15s " + Colors.RESET +
                            Colors.MAGENTA + "%-24s " + Colors.RESET +
                            Colors.BRIGHT_CYAN + "║%n" + Colors.RESET,
                    process.getName(),
                    process.getBurstTime() + " ms",
                    process.getWaitingTime() + " ms");
        }

        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                          "╚══════════════════════════════════════════════════════════════╝" +
                          Colors.RESET + "\n");
    }

    // Method to add a process to the queue and map, while printing a "ready" message
    public static void addProcessToQueue(Process process, Queue<Thread> processQueue,
                                        Map<Thread, Process> processMap) {
        // Create a new thread to run the process
        Thread thread = new Thread(process);

        // Feature 3: record time when process enters ready queue
        process.markEnqueued();

        // Add the thread to the ready queue
        processQueue.add(thread);

        // Map the thread to the process, so we can track the process associated with each thread
        processMap.put(thread, process);

        // Feature 1: Show process priority when it enters the ready queue
        System.out.println(Colors.BLUE + "  ➕ " + Colors.BOLD + Colors.CYAN + process.getName() +
                          Colors.RESET + Colors.BLUE + " added to ready queue" + Colors.RESET +
                          " │ Burst time: " + Colors.YELLOW + process.getBurstTime() + "ms" +
                          Colors.RESET +
                          " │ Priority: " + Colors.BRIGHT_YELLOW + process.getPriority() + Colors.RESET);
    }
}
