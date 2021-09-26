package com.green.wallet.binanceapi;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class JavaTestMain {
    public static void main(String[] args) throws ParseException {
        long myLongDateAndTime = 1180971576;
        Instant instant = Instant.ofEpochMilli(myLongDateAndTime);

        System.out.println(instant);
    }
}
