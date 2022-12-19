/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.os;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author DELL
 */
public class Customer implements Runnable {

    private Saloon saloon;
    

    public Customer(Saloon saloon) {
        this.saloon = saloon;
    }
    @Override
    public void run() {
        sleepForSomeTime();
        walkIntoSaloon();
    }
    private void walkIntoSaloon(){
        saloon.leaveSaloon();
    }

    private void sleepForSomeTime() {
        int duration = new Random().nextInt(10);
        try{
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("%s arrived after %d seconds \n",Thread.currentThread().getName(),duration);

        }
    }
    

