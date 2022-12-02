package com.ute.sportswearbe.services.payment;

import com.ute.sportswearbe.dtos.PaymentDto;
import com.ute.sportswearbe.entities.Payment;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Principal principal, PaymentDto paymentDto);

    Page<Payment> getPaymentByPrincipal(Principal principal);

    Payment getPaymentByID(String id);

    Page<Payment> getPaymentPaging(String search, int page, int size, String sort, String column);

    List<Payment> getAllPayment();
}
