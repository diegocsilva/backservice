package com.example.backservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "name")
    private String name;
    @Column(name = "businessArea")
    private String businessArea;
}
