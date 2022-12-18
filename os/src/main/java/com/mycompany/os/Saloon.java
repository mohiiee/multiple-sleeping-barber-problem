/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DELL
 */
public class Saloon {
    private Semaphore barberChair;
    private Semaphore barberLock;
    private Semaphore customerLock;
    private AtomicInteger customerCount;
    private int noOfWaitingChairs;
    private Lock lock;

    public Saloon(Semaphore barberLock, Semaphore customerLock, int noOfWaitingChairs) {
        this.barberLock = barberLock;
        this.customerLock = customerLock;
        this.noOfWaitingChairs = noOfWaitingChairs;
        this.customerCount =new AtomicInteger(0);
        this.barberChair = new Semaphore (1);
        this.lock = new ReentrantLock();
    }
    public void leaveSaloon(){
        lock.lock();
        if(isSaloonFull()){
            System.out.printf("%s leaves at %s\n",Thread.currentThread().getName(),new Date());
            lock.unlock();
            return;
        }
        lock.unlock();
        customerCount.incrementAndGet();
        try {
            barberChair.acquire();
            customerCount.decrementAndGet();
            System.out.printf("%s having haircut at %s\n",Thread.currentThread().getName(),new Date());
            barberLock.release();
            customerLock.acquire();
            System.out.printf("%s completed haircut at %s\n",Thread.currentThread().getName(),new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            barberChair.release();
        }
        
}
    private boolean isSaloonFull(){
        return customerCount.get() == noOfWaitingChairs;
    }
}
/* comment */
