/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Barber implements Runnable{

    private Semaphore barberLock;
    private Semaphore customerLock;
    public Barber(Semaphore barberLock, Semaphore customerLock) {
        this.barberLock = barberLock;
        this.customerLock = customerLock;
    }
    @Override
    public void run() {
        while (true){
            try {
                barberLock.acquire();
                doHairCut();
                customerLock.release();
            } catch (InterruptedException e) {
                return;
            }
        }
        
    }

    private void doHairCut() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
        }
    }
    
}
