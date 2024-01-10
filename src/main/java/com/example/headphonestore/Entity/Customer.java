package com.example.headphonestore.Entity;

import com.example.headphonestore.Shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String userName;
    private String email;
    private String password;
    private Status status;
    @OneToMany(mappedBy = "customer")
    private List<Bill> bills;
}
