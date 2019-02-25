package com.example.backservice.repository;

import com.example.backservice.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.ResultSet;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query(nativeQuery = true,
            value = "select idsale, sum(price) from sale s " +
                    "left join sale_items si on si.sale_idSale = s.idsale " +
                    "left join item i on si.items_itemid = i.itemid " +
                    "order by sum(price) desc")
    Object[] findMostExpensive();

}
