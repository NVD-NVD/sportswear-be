package com.ute.sportswearbe.services.payment;

import com.ute.sportswearbe.dtos.PaymentDto;
import com.ute.sportswearbe.entities.Payment;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.repositories.PaymentRepository;
import com.ute.sportswearbe.services.user.UserService;
import com.ute.sportswearbe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserService userService;
    @Override
    public Payment createPayment(Principal principal, PaymentDto paymentDto) {
        User user = getUser(principal);

        return null;
    }

    @Override
    public Page<Payment> getPaymentByPrincipal(Principal principal) {
        return null;
    }

    @Override
    public Payment getPaymentByID(String id) {
        return null;
    }

    @Override
    public Page<Payment> getPaymentPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page,size,sort,column);
        return paymentRepository.getPaymentPaging(search, pageable);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    private User getUser(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        if (user == null)
            throw new InvalidException("User không tồn tại.");
        return user;
    }

}
