package com.test.ticketsalesapplication.util;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Random;

@Component
public class PaymentStatusGenerator {
    ArrayList<String> statusList = new ArrayList();

    public String getRandomStatus(Integer id){
        statusList.add("NEW");
        statusList.add("FAILED");
        statusList.add("DONE");

        return statusList.get(new Random().nextInt(2 - 0 + 1));
    }
}
