/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ozzy.paymenttracker;

/**
 *
 * @author filip
 */
public class Payment {
    private final String currency;
    private final Long value;


    private Payment(String cur, Long val) {
        currency = cur;
        value = val;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getValue() {
        return value;
    }

    public static Payment parse(String s) {
        if (s.length() < 3) {
            System.out.println("No currency-value pair provided.");
            return null;
        }
        String cur = s.substring(0, 3);
        if (!cur.matches("^[A-Z]{3}")) {
            System.out.print("Currency does not consist of upper case ASCI letters only.");
            return null;
        }
        Long decode;
        try {
            decode = Long.decode(s.substring(3, s.length()).trim());
        } catch (NumberFormatException e) {
            System.out.println("Wrong input could not parse the value of type Long.");
            return null;
        }
        //BigDecimal val = new BigDecimal(s.substring(3, s.length()).trim());
        return new Payment(cur, decode);
    }
}
