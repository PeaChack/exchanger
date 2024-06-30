package by.peachack.exchanger.mapper.nbrb;

import by.peachack.exchanger.dto.nbrb.NbrbCurrencyDTO;
import by.peachack.exchanger.dto.nbrb.NbrbCurrencyRateDTO;
import by.peachack.exchanger.model.Currency;
import by.peachack.exchanger.model.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class NbrbCurrencyMapper {
    //TODO impl
    @Mapping(source = "id", target = "currency.id")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "rate", target = "officialRate")
    @Mapping(source = "abbreviation", target = "currency.abbreviation")
    public abstract CurrencyRate NbrbRateToRate(NbrbCurrencyRateDTO dto);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "abbreviation", target = "abbreviation")
    @Mapping(source = "name", target = "name")
    public abstract Currency NbrbCurrencyToCurrency(NbrbCurrencyDTO dto);
}
