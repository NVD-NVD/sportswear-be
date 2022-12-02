package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.entities.Payment;
import com.ute.sportswearbe.services.payment.PaymentService;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.build.RepeatedAnnotationPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "Create payment")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEMBER')")
    public ResponseEntity<?> createPayment(
            Principal principal,
            @RequestParam(value = "gateway") String gateway,
            @RequestParam(value = "orderID") String orderID,
            @RequestParam(value = "amount") double amount
    ){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @ApiOperation(value = "Payments paging", notes = "Admin")
    @GetMapping("/paging")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Payment>> getPaymentsPaging(
        @RequestParam(value = "search", required = false, defaultValue = "") String search,
        @RequestParam(value = "page", required = false, defaultValue = "${paging.default.page}") int page,
        @RequestParam(value = "size", required = false, defaultValue = "${paging.default.size}") int size,
        @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
        @RequestParam(value = "column", required = false, defaultValue = "createDate") String column
    ){

        return new ResponseEntity<>(paymentService.getPaymentPaging(search,page,size,sort,column), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all payments", notes = "Admin")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPayments(){
        return  new ResponseEntity<>(paymentService.getAllPayment(), HttpStatus.OK);
    }

}
