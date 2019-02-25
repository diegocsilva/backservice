package com.example.backservice.dto;

import com.example.backservice.model.Customer;
import com.example.backservice.model.Sale;
import com.example.backservice.model.Saleman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private List<Customer> customers = new ArrayList<>();
    private List<Saleman> salesman = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
}
