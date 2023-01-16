package com.test.ticketsalesapplication.util.service.impl;

import com.test.ticketsalesapplication.dao.repository.PaymentRepository;
import com.test.ticketsalesapplication.dto.PaymentDto;
import com.test.ticketsalesapplication.facade.PaymentFacade;
import com.test.ticketsalesapplication.service.impl.PaymentServiceImpl;
import com.test.ticketsalesapplication.util.PaymentStatusGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentServiceImpl.class,
                           PaymentStatusGenerator.class, PaymentFacade.class})
public class PaymentServiceImplTest {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @MockBean
    private PaymentStatusGenerator paymentStatusGenerator;

    @MockBean
    private PaymentFacade paymentFacade;

    @MockBean
    private PaymentRepository paymentRepository;

    private PaymentDto paymentDto = new PaymentDto();

    private int testId = 1;

    @Test
    public void findPaymentByIdTest_shouldCallRepositoryMethod(){
        paymentServiceImpl.findPaymentById(testId);

        verify(paymentRepository, Mockito.times(1)).getById(Mockito.any());
    }

    @Test
    public void savePaymentTest_shouldCallRepositoryMethod(){
        paymentServiceImpl.savePayment(paymentDto);

        verify(paymentRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void savePaymentTest_shouldCallFacadeMethod(){
        paymentServiceImpl.savePayment(paymentDto);

        verify(paymentFacade, Mockito.times(1)).mapPaymentDto(Mockito.any());
    }

    @Test
    public void getPaymentStatus_shouldCallGeneratorMethod(){
        paymentServiceImpl.getPaymentStatus(testId);

        verify(paymentStatusGenerator, Mockito.times(1)).getRandomStatus(Mockito.anyInt());
    }

    @Test
    public void findByTicketId_shouldCallRepositoryMethod(){
        paymentServiceImpl.findByTicketId(testId);

        verify(paymentRepository, Mockito.times(1)).findByTicketId(Mockito.anyInt());
    }
}
