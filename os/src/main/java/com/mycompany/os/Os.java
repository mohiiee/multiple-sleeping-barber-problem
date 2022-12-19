package com.mycompany.os;

import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Os {

    public static void main(String[] args) throws InterruptedException {
        int noOfCustomers = Runtime.getRuntime().availableProcessors();

        Thread[] threads = new Thread[noOfCustomers];

        Semaphore barberLock =new Semaphore(0);
        Semaphore customerLock =new Semaphore(0);
        System.out.print("enter no of waiting chairs: ");
        Scanner sc= new Scanner(System.in);
        int x =sc.nextInt();
        Saloon saloon =new Saloon (barberLock,customerLock,x);
        Barber barber = new Barber(barberLock,customerLock);
        Thread barberThread = new Thread(barber);
        barberThread.start();
        
        for (int i =0;i<noOfCustomers ;i++){

            Thread thread = new Thread(new Customer(saloon));

            thread.setName("Customer: "+i);

            threads[i]=thread;
            threads[i].start();
            threads[i].join();
            
        }
       
        barberThread.interrupt();
    }
}
