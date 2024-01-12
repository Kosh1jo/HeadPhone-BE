package com.example.headphonestore.Controller;

import com.example.headphonestore.Dto.BaseResponse;
import com.example.headphonestore.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping
    public ResponseEntity<BaseResponse> getAllPayment(){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), paymentService.getAllPayment(),"Get all payment complete"));
    }
}
