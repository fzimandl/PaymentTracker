/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozzy.paymenttracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip
 */
public class Main {

    public static void main(String[] args) throws IOException {
        List<Payment> pays = new ArrayList<>();
        if (args.length > 0) {
            try {
                File f = new File(args[0]);
                System.out.println(f.exists() + f.getPath());
                if (!f.exists() && Main.class.getResource(args[0]) != null)
                    f = new File(Main.class.getResource(args[0]).toURI());

                List<String> readAllLines = Files.readAllLines(Paths.get(f.getPath()));
                readAllLines.forEach(l -> {
                    Payment p = Payment.parse(l);
                    if (p != null)
                        pays.add(p);
                });
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Could not load specified transaction file.", ex);
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        Timer t = Timer.getInstance(pays);
        t.start();
        while (!s.equals("quit")) {
            s = br.readLine();
            if (!s.equals("quit")) {
                Payment p = Payment.parse(s);
                if (p != null)
                    pays.add(p);
            }
        }
        t.interrupt();

    }

}
