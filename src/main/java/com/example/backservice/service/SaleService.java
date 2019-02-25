package com.example.backservice.service;

import com.example.backservice.dto.FileDTO;
import com.example.backservice.dto.MostSaleDTO;
import com.example.backservice.model.Sale;
import com.example.backservice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SaleRepository repository;

    public Sale buildSaleByString(StringBuilder entity) {
        String[] fields = entity.toString().split("ç");
        Sale sale = new Sale();
        sale.setIdSale(Integer.valueOf(fields[1]));
        sale.setItems(itemService.buildItemsByString(fields[2]));
        sale.setSalesmanName(fields[3]);
        return sale;
    }

    public void saveAll(FileDTO fileDTO) {
        fileDTO.getSales().forEach(sale -> {
            itemService.saveAll(sale.getItems());
            repository.save(sale);
        });
    }

    public MostSaleDTO findMostExpensive() {
        Object[] result = repository.findMostExpensive();
        return new MostSaleDTO(result);
    }
}
