package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.models.PaymentResp;
import com.ute.sportswearbe.services.payment.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/rest/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "Create payment")
    @PostMapping
    @PreAuthorize("hasRole('MENBER')")
    public ResponseEntity<?> createPayment(
            Principal principal,
            @RequestParam(value = "gateway") String gateway,
            @RequestParam(value = "orderID") String orderID,
            @RequestParam(value = "url_return") String url_return ) throws IOException {
        PaymentResp resp = paymentService.createPayment(orderID, url_return, principal);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @ApiOperation(value = "Thông tin thanh toán sau khi thanh toán thành công")
    @GetMapping("/thong-tin-thanh-toan")
    @PreAuthorize("hasRole('MENBER')")
    public void uploadPayment(
                  @RequestParam(value = "vnp_Amount", required = false) String amount,
          @RequestParam(value = "vnp_BankCode", required = false) String bankCode,
          @RequestParam(value = "vnp_BankTranNo", required = false) String bankTranNo,
          @RequestParam(value = "vnp_CardType", required = false) String cardType,
          @RequestParam(value = "vnp_OrderInfo", required = false) String orderInfo,
          @RequestParam(value = "vnp_PayDate", required = false) String payDate,
          @RequestParam(value = "vnp_ResponseCode", required = false) String responseCode,
          @RequestParam(value = "vnp_TmnCode", required = false) String tmnCode,
          @RequestParam(value = "vnp_TransactionNo", required = false) String transactionNo,
          @RequestParam(value = "vnp_TxnRef", required = false) String txnRef,
          @RequestParam(value = "vnp_SecureHashType", required = false) String secureHashType,
          @RequestParam(value = "vnp_SecureHash", required = false) String secureHash,
          Principal principal) {
        paymentService.transactionHandle(amount, bankCode, bankTranNo, cardType, orderInfo,
                payDate, responseCode, tmnCode, transactionNo, txnRef, secureHashType, secureHash, principal);
    }

}
