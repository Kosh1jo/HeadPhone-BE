package com.example.headphonestore.Repository;

import com.example.headphonestore.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}