package com.example.headphonestore.Service.ServiceImpl;

import com.example.headphonestore.Convert.CustomerConvert;
import com.example.headphonestore.Dto.CustomerDto;
import com.example.headphonestore.Entity.Customer;
import com.example.headphonestore.Repository.CustomerRepository;
import com.example.headphonestore.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConvert convert;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConvert convert) {
        this.customerRepository = customerRepository;
        this.convert = convert;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customer ->
             new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getUserName(),
                    customer.getEmail(),
                    customer.getPassword(),
                    customer.getStatus())
       ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setStatus(dto.getStatus());
        customer.setUserName(dto.getUsername());
        customer.setPassword(dto.getPassword());
        Customer saved = customerRepository.save(customer);
        return convert.toCustomerDto(saved);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id).get();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setStatus(dto.getStatus());
        customer.setUserName(dto.getUsername());
        customer.setPassword(dto.getPassword());
        Customer saved = customerRepository.save(customer);
        return convert.toCustomerDto(saved);
    }

    @Override
    @Transactional
    public String deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return "Delete";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail delete";
    }
}
