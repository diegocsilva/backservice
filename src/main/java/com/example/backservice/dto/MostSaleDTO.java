package com.example.backservice.dto;

public class MostSaleDTO {

    private Integer idSale;
    private Double price;

    public MostSaleDTO(Object[] result) {
        Object[] sale = (Object[]) result[0];
        this.idSale = (Integer) sale[0];
        this.price = (Double) sale[1];
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
