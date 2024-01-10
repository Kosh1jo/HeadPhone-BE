package com.example.headphonestore.Dto;

import com.example.headphonestore.Shared.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String username;
    private String email;
    private String password;
    private Status status;
}
