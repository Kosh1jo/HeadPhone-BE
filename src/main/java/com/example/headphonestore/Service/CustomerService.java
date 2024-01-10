package com.example.headphonestore.Service;

import com.example.headphonestore.Dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerDto dto);

    CustomerDto updateCustomer(Long id, CustomerDto dto);

    String deleteCustomer(Long id);
}
