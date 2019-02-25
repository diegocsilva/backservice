package com.example.backservice.service;

import com.example.backservice.dto.FileDTO;
import com.example.backservice.model.Customer;
import com.example.backservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer buildCustomerByString(StringBuilder entity) {
        String[] fields = entity.toString().split("ç");
        Customer customer = new Customer();
        customer.setCnpj(fields[1]);
        customer.setName(fields[2]);
        customer.setBusinessArea(fields[3]);
        return customer;
    }

    public void saveAll(FileDTO fileDTO) {
        repository.saveAll(fileDTO.getCustomers());
    }

    public long count() {
        return repository.count();
    }
}
