package by.peachack.exchanger.dto.nbrb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NbrbCurrencyDTO {
    @JsonProperty("Cur_ID")
    Integer id;
    @JsonProperty("Cur_Code")
    Integer code;
    @JsonProperty("Cur_Abbreviation")
    String abbreviation;
    @JsonProperty("Cur_Name")
    String name;
}
