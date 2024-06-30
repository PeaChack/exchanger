package by.peachack.exchanger.dto.nbrb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NbrbCurrencyRateDTO {
    @JsonProperty("Cur_ID")
    private Integer id;
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;
    @JsonProperty("Cur_Scale")
    private Integer scale;
    @JsonProperty("Cur_OfficialRate")
    private Double rate;
}
