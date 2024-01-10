package com.example.headphonestore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailDto {
    private Long id;
    private Long billId;
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}
