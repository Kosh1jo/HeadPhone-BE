package com.example.headphonestore.Controller;

import com.example.headphonestore.Dto.BaseResponse;
import com.example.headphonestore.Dto.CustomerDto;
import com.example.headphonestore.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCustomers(){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                customerService.getAllCustomers(),
                "Get all Customers complete"));
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createCustomer(@RequestBody CustomerDto dto){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), customerService.createCustomer(dto),"Create customer complete"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto dto){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), customerService.updateCustomer(id,dto),"Update customer complete"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), customerService.deleteCustomer(id),"Delete product complete"));
    }
}
