package com.example.headphonestore.Convert;

import com.example.headphonestore.Dto.CustomerDto;
import com.example.headphonestore.Dto.ProductDto;
import com.example.headphonestore.Entity.Customer;
import com.example.headphonestore.Entity.Product;
import com.example.headphonestore.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvert {
    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto result = new CustomerDto();
        if(customer.getId() != null){
            result.setId(customer.getId());
        }
        result.setName(customer.getName());
        result.setEmail(customer.getEmail());
        result.setAddress(customer.getAddress());
        result.setPhone(customer.getPhone());
        result.setStatus(customer.getStatus());
        result.setUsername(customer.getUserName());
        result.setPassword(customer.getPassword());
        return result;
    }
    public Customer toCustomer(CustomerDto customerDto) {
        Customer result= new Customer();
        result.setName(customerDto.getName());
        result.setEmail(customerDto.getEmail());
        result.setAddress(customerDto.getAddress());
        result.setPhone(customerDto.getPhone());
        result.setStatus(customerDto.getStatus());
        result.setUserName(customerDto.getUsername());
        result.setPassword(customerDto.getPassword());
        return result;
    }
}
