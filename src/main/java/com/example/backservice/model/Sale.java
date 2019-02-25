package com.example.backservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="sale")
public class Sale {
    @Id
    @Column(name = "idsale")
    private Integer idSale;

    @Column(name = "salesmanName")
    private String salesmanName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Item> items;
}
