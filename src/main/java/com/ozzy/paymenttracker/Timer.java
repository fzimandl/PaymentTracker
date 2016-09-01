/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozzy.paymenttracker;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 *
 * @author filip
 */
public class Timer extends Thread {

    private final List<Payment> pays;
    private static Timer INSTANCE = null;

    private Timer(List<Payment> pays) {
        this.pays = pays;
    }

    // Lazy Initialization (If required then only)
    protected static Timer getInstance(List<Payment> pays) {
        synchronized (Timer.class) {
            if (INSTANCE == null) {
                INSTANCE = new Timer(pays);
            }
        }
        return INSTANCE;
    }

    @Override
    public void run() {
        //boolean run = true;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(" ---- current state of all transactions made ----"); //print amounts
            /*pays.forEach(p -> {
             System.out.println(p.getCurrency() + " " + p.getValue());
             });*/
            Set<String> collect = pays.stream().map(p -> p.getCurrency()).collect(Collectors.toSet());
            collect.stream().forEach((s) -> {
                long sum = pays.stream().filter(p -> p.getCurrency().equals(s)).mapToLong(Payment::getValue).sum();
                if (sum != 0) {
                    System.out.println(s + " " + sum);
                }
            });

            System.out.println("\n");
            try {
                sleep(60000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, "Interrupting Timer thread.", ex);
                interrupt();
            }
        }
    }

}
