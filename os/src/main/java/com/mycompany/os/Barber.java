/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
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
                System.out.printf("waking up\n ");
                doHairCut();
                customerLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        
    }

    private void doHairCut() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
