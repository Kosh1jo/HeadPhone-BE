package com.example.headphonestore.Repository;

import com.example.headphonestore.Entity.Bill;
import com.example.headphonestore.Entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value = "select u from BillDetail u where" +
            " u.bill.id =:id")
    List<BillDetail> findBillDetailsByBillId(Long id);
    BillDetail findBillDetailById(Long id);
}