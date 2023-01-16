package com.test.ticketsalesapplication.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentStatusGenerator.class)
public class PaymentStatusGeneratorTest {

    @Autowired
    PaymentStatusGenerator paymentStatusGenerator;

    private int testId = 1;
    private String expectedStatus1 = "NEW";
    private String expectedStatus2 = "FAILED";
    private String expectedStatus3 = "DONE";
    private String actualStatus;

    @Test
    public void testGetRandomStatus_shouldReturnExpectedStatus(){
        actualStatus = paymentStatusGenerator.getRandomStatus(testId);

        assertTrue(expectedStatus1.equals(actualStatus)
                || expectedStatus2.equals(actualStatus)
                || expectedStatus3.equals(actualStatus));
    }
}
