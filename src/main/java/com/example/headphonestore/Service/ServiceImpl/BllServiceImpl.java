package com.example.headphonestore.Service.ServiceImpl;

import com.example.headphonestore.Dto.BillDetailDto;
import com.example.headphonestore.Dto.BillDto;
import com.example.headphonestore.Entity.Bill;
import com.example.headphonestore.Entity.BillDetail;
import com.example.headphonestore.Entity.PaymentMethod;
import com.example.headphonestore.Entity.Product;
import com.example.headphonestore.Repository.*;
import com.example.headphonestore.Service.BillService;
import com.example.headphonestore.Shared.StatusBill;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BllServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final BillDetailRepository billDetailRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public BllServiceImpl(BillRepository billRepository, PaymentMethodRepository paymentMethodRepository, BillDetailRepository billDetailRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.billRepository = billRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.billDetailRepository = billDetailRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
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

    @Override
    @Transactional
    public BillDto updateBill(Long id, BillDto dto) {
        Bill bill = billRepository.findById(id).get();
        PaymentMethod paymentMethod = paymentMethodRepository.findById(Long.valueOf(dto.getPaymentId())).get();
        bill.setPayment(paymentMethod);
        bill.setBillDate(dto.getBillDate());
        bill.setShippingAddress(dto.getShippingAddress());
        StatusBill statusBill = StatusBill.valueOf(dto.getStatusBill());
        bill.setStatusBill(statusBill);
        bill.setBillDetails(dto.getBillDetailList().stream().map(billDetailDto -> {
            Product product = productRepository.findProductByName(billDetailDto.getProductName());
//            Product product = productRepository.getProductById(billDetailDto.getProductId());
//            BillDetail billDetail =  billDetailRepository.findById(billDetailDto.getId()).get();
//            billDetail.setQuantity(billDetailDto.getQuantity());
//            billDetail.setProduct(product);
//            billDetail.setPrice(product.getPrice());
//            return billDetailRepository.save(billDetail);
                return billDetailRepository.save(new BillDetail(billDetailDto.getId(), bill, product, billDetailDto.getQuantity(), product.getPrice()));
                }).collect(Collectors.toList()));
        bill.setCustomer(customerRepository.findById(dto.getUserId()).get());
        double totalAmount = dto.getBillDetailList().stream().map(billDetailDto -> {
            Product product = productRepository.findProductByName(billDetailDto.getProductName());
//            Product product = productRepository.getProductById(billDetailDto.getProductId());
            return product.getPrice()* billDetailDto.getQuantity();
        })   .reduce((double) 0, Double::sum);
        bill.setTotalAmount(totalAmount);
        Bill saved = billRepository.save(bill);
        List<BillDetailDto> billDetailDtoList = saved.getBillDetails().stream()
                .map(billDetail -> {
                    BillDetailDto billDetailDto = new BillDetailDto();
                    billDetailDto.setBillId(billDetail.getBill().getId());
                    billDetailDto.setId(billDetail.getId());
                    billDetailDto.setPrice(billDetail.getPrice());
                    billDetailDto.setQuantity(billDetail.getQuantity());
                    billDetailDto.setProductId(billDetail.getProduct().getId());
                    billDetailDto.setProductName(billDetail.getProduct().getName());
                    return billDetailDto;
                }).toList();
        return new BillDto(saved.getId(),saved.getCustomer().getId(),saved.getCustomer().getEmail(),saved.getCustomer().getUserName(),saved.getPayment().getId().toString(),saved.getPayment().getTypePayment().toString(),null,billDetailDtoList,saved.getBillDate(),saved.getStatusBill().toString(),saved.getTotalAmount(),saved.getShippingAddress());
    }

    @Override
    @Transactional
    public String deleteBill(Long id) {
        try {
            billRepository.deleteById(id);
            return "Delete complete";
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    @Transactional
    public BillDto deleteBillDetailInBill(Long id, Long detailId) {
        List<BillDetail> billDetails = billDetailRepository.findBillDetailsByBillId(id);
        billDetails.remove(billDetailRepository.findBillDetailById(detailId));
        Bill bill = billRepository.findById(id).get();
        bill.setBillDetails(billDetails);
        billDetailRepository.deleteById(detailId);
        double totalAmount = billDetails.stream().map(billDetail -> {
            Product product = productRepository.getProductById(billDetail.getProduct().getId());
            return product.getPrice() * billDetail.getQuantity();
        })   .reduce((double) 0, Double::sum);
        bill.setTotalAmount(totalAmount);
        Bill saved = billRepository.save(bill);
        BillDto billDto = new BillDto();
        billDto.setUsername(saved.getCustomer().getUserName());
        billDto.setBillDate(saved.getBillDate());
        billDto.setUserId(saved.getCustomer().getId());
        billDto.setEmail(saved.getCustomer().getEmail());
        billDto.setStatusBill(saved.getStatusBill().toString());
        billDto.setBillDetailList(saved.getBillDetails().stream()
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
    }
}
