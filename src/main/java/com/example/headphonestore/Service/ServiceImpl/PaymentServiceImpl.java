package com.example.headphonestore.Service.ServiceImpl;

import com.example.headphonestore.Dto.PaymentDto;
import com.example.headphonestore.Repository.PaymentMethodRepository;
import com.example.headphonestore.Service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<PaymentDto> getAllPayment() {
        return paymentMethodRepository.findAll().stream().map(
                paymentMethod -> new PaymentDto(
                        paymentMethod.getId(),
                        paymentMethod.getTypePayment())
        ).collect(Collectors.toList());
    }
}
