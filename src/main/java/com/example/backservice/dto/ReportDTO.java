package com.example.backservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Long amountCustomer;
    private Long amountSaleman;
    private MostSaleDTO IDSaleRecord;
    private Integer badSeller;
}
