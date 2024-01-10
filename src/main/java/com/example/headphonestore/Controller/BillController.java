package com.example.headphonestore.Controller;

import com.example.headphonestore.Dto.BaseResponse;
import com.example.headphonestore.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bills")
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping
    public ResponseEntity<BaseResponse> getAllBills(){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                billService.getAllBills(),"Get all bill complete"));
    }
}
