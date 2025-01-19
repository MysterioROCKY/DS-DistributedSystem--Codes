import java.util.concurrent.locks.ReentrantLock;

class SharedCounter {
    private int counter = 0;
    private ReentrantLock lock = new ReentrantLock();

    // Method to safely increment the counter
    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            counter++; // Increment the shared counter
        } finally {
            lock.unlock(); // Ensure the lock is released even if an exception occurs
        }
    }

    // Method to get the current counter value
    public int getValue() {
        return counter;
    }
}

class IncrementTask implements Runnable {
    private SharedCounter sharedCounter;

    public IncrementTask(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            sharedCounter.increment(); // Safely increment the counter
        }
    }
}

public class SharedMemoryExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sanyam Agrawal SE21UCSE192");


        SharedCounter sharedCounter = new SharedCounter();
        
        // Create multiple threads
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new IncrementTask(sharedCounter));
            threads[i].start(); // Start each thread
        }
        
        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }
        
        // Print the final counter value
        System.out.println("Final counter value: " + sharedCounter.getValue());
    }
}