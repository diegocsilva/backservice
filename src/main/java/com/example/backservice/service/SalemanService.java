package com.example.backservice.service;

import com.example.backservice.dto.FileDTO;
import com.example.backservice.model.Saleman;
import com.example.backservice.repository.SalemanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalemanService {

    @Autowired
    private SalemanRepository repository;

    public Saleman buildSalemanByString(StringBuilder entity) {
        String[] fields = entity.toString().split("ç");
        Saleman saleman = new Saleman();
        saleman.setCpf(fields[1]);
        saleman.setName(fields[2]);
        saleman.setSalario(Double.parseDouble(fields[3]));
        return saleman;
    }

    public void saveAll(FileDTO fileDTO) {
        repository.saveAll(fileDTO.getSalesman());
    }

    public long count() {
        return repository.count();
    }
}
