package com.example.headphonestore.Service;

import com.example.headphonestore.Dto.BillDto;
import com.example.headphonestore.Entity.Bill;

import java.util.List;

public interface BillService {
    List<BillDto> getAllBills();

    BillDto updateBill(Long id, BillDto dto);

    String deleteBill(Long id);

    BillDto deleteBillDetailInBill(Long id, Long detailId);

    BillDto createBill(BillDto dto);
}
