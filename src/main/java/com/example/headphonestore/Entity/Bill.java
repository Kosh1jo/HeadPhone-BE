package com.example.headphonestore.Entity;

import com.example.headphonestore.Shared.StatusBill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentMethod payment;
    @OneToMany(mappedBy ="bill")
    private List<BillDetail> billDetails;
    private Date billDate;
    private StatusBill statusBill;
    private double totalAmount;
    private String shippingAddress;
}
