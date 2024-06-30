package by.peachack.exchanger.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CurrencyRateDTO {
    private String currency_name;
    private String currency_abbreviation;
    private Integer scale;
    private LocalDate date;
    private Double officialRate;
}
