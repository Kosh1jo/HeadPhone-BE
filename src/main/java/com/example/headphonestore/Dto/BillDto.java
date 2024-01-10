package com.example.headphonestore.Dto;

import com.example.headphonestore.Entity.Bill;
import com.example.headphonestore.Entity.BillDetail;
import com.example.headphonestore.Entity.Customer;
import com.example.headphonestore.Entity.PaymentMethod;
import com.example.headphonestore.Shared.StatusBill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Long id;
    private Long userId;
    private String email;
    private String username;
    private String paymentId;
    private String paymentName;
    private Long[] billDetailIds;
    private List<BillDetailDto> billDetailList;
    private Date billDate;
    private String statusBill;
    private double totalAmount;
    private String shippingAddress;
}
