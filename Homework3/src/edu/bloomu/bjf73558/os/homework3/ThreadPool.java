package edu.bloomu.bjf73558.os.homework3;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Brian Fekete
 */
public class ThreadPool {

    private final Lock lock = new ReentrantLock();
    final static int MAX_TASKS = 3;
    WorkQueue taskQueue;

    /**
     * Constructor creates an array of threads and instantiates the work queue.
     * It then fills the array with WorkerThread objects and starts them. The
     * size of an array should be determined by a static constant (initialized
     * to 3 for testing purposes). The only other method needed is one that adds
     * a given task to the queue.
     */
    public ThreadPool() {

        Thread tasks[] = new Thread[MAX_TASKS];
        for (int i = 0; i < MAX_TASKS; i++) {
            tasks[i] = new Thread();
            tasks[i].start();
        }
    }

    public void addTaskToQueue(Connection connection) {
        lock.lock();
        try {
            taskQueue.addTaskToQueue(connection);
        } finally {
            lock.unlock();
        }
    }

}

/**
 * Stores a Linked List of runnable tasks. Use LinkedList, ReentrantLock, and
 * Condition. You need one method to add a task to the list and another to get a
 * task. A thread will block if it tries to to get a task when none is
 * available.
 *
 * @author Brian Fekete
 */
class WorkQueue {

    LinkedList<Connection> taskQueue;

    public WorkQueue() {
        taskQueue = new LinkedList<>();
    }

    public void addTaskToQueue(Connection connection) {
        taskQueue.add(connection);
    }
}

/**
 * Simply tries to get a task from the queue and runs it.
 *
 * @author Brian Fekete
 */
class WorkerThread implements Runnable {

    @Override
    public void run() {

    }

}
