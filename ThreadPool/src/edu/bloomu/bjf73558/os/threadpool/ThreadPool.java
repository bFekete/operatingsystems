package edu.bloomu.bjf73558.os.threadpool;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian Fekete
 */
public class ThreadPool {

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
        taskQueue = new WorkQueue();

        Thread tasks[] = new Thread[MAX_TASKS];
        for (int i = 0; i < MAX_TASKS; i++) {
            tasks[i] = new Thread(new WorkerThread());
            tasks[i].start();
        }
    }

    public void addTaskToQueue(Connection connection) {
        taskQueue.addTaskToQueue(connection);
    }

    /**
     * Stores a Linked List of runnable tasks. Use LinkedList, ReentrantLock,
     * and Condition. You need one method to add a task to the list and another
     * to get a task. A thread will block if it tries to to get a task when none
     * is available.
     *
     * @author Brian Fekete
     */
    class WorkQueue {

        LinkedList<Connection> taskQueue;

        private final Lock lock = new ReentrantLock();
        private final Condition notEmpty = lock.newCondition();

        public WorkQueue() {
            taskQueue = new LinkedList<>();
        }

        public void addTaskToQueue(Connection connection) {
            lock.lock();
            try {

                taskQueue.add(connection);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public Connection getTaskFromQueue() {
            lock.lock();
            try {
                while (taskQueue.isEmpty()) {
                    notEmpty.await();
                }
                return taskQueue.pop();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted exception in getTaskFromQueue\n" + ex.toString());
            } finally {
                lock.unlock();
            }
            return null;
        }

    } // End of WorkQueue

    /**
     * Simply tries to get a task from the queue and runs it.
     *
     * @author Brian Fekete
     */
    class WorkerThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Connection connection = taskQueue.getTaskFromQueue();
                    Thread thread = new Thread(connection);
                    thread.start();
                    thread.join();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted exception in WorkerThread\n" + ex.toString());
                }
            }
        }

    }

}
