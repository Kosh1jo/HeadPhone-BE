package com.example.headphonestore.Controller;

import com.example.headphonestore.Dto.BaseResponse;
import com.example.headphonestore.Dto.BillDto;
import com.example.headphonestore.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createBill(@RequestBody BillDto dto) {
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                billService.createBill(dto),"Create bill complete"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateBill(@PathVariable(name = "id") Long id, @RequestBody BillDto dto) {
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                billService.updateBill(id,dto),"Update all bill complete"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteBill(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                billService.deleteBill(id),"Delete bill complete"));
    }
    @DeleteMapping("/delete/{id}/{detailId}")
    public ResponseEntity<BaseResponse> deleteBillDetailInBill(@PathVariable(name = "id") Long id, @PathVariable(name = "detailId") Long detailId){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                billService.deleteBillDetailInBill(id,detailId),"Delete bill detail in bill complete"));
    }
}
