package com.example.headphonestore.Service.ServiceImpl;

import com.example.headphonestore.Dto.BillDetailDto;
import com.example.headphonestore.Dto.BillDto;
import com.example.headphonestore.Dto.CustomerDto;
import com.example.headphonestore.Entity.Bill;
import com.example.headphonestore.Entity.BillDetail;
import com.example.headphonestore.Repository.BillRepository;
import com.example.headphonestore.Service.BillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BllServiceImpl implements BillService {
    private final BillRepository billRepository;

    public BllServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDto> getAllBills() {
        List<Bill> billList = billRepository.findAll();
        return billList.stream().map(bill -> {
            BillDto billDto = new BillDto();
            billDto.setUsername(bill.getCustomer().getUserName());
            billDto.setBillDate(bill.getBillDate());
            billDto.setUserId(bill.getCustomer().getId());
            billDto.setEmail(bill.getCustomer().getEmail());
            billDto.setStatusBill(bill.getStatusBill().toString());
//            billDto.setBillDetailIds(
//                    bill.getBillDetails().stream()
//                            .map(BillDetail::getId)
//                            .toArray(Long[]::new)
//            );
            billDto.setBillDetailList(bill.getBillDetails().stream()
                    .map(billDetail -> {
                        BillDetailDto billDetailDto = new BillDetailDto();
                        billDetailDto.setBillId(billDetail.getBill().getId());
                        billDetailDto.setId(billDetail.getId());
                        billDetailDto.setPrice(billDetail.getPrice());
                        billDetailDto.setQuantity(billDetail.getQuantity());
                        billDetailDto.setProductId(billDetail.getProduct().getId());
                        billDetailDto.setProductName(billDetail.getProduct().getName());
                        return billDetailDto;
                    }).collect(Collectors.toList()));
            billDto.setTotalAmount(bill.getTotalAmount());
            billDto.setPaymentId(bill.getPayment().getId().toString());
            billDto.setPaymentName(bill.getPayment().getTypePayment().name());
            billDto.setShippingAddress(bill.getShippingAddress());
            billDto.setId(bill.getId());
            return billDto;
        }).collect(Collectors.toList());
    }
}
