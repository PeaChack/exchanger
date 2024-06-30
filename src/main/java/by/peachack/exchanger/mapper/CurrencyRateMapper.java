package by.peachack.exchanger.mapper;

import by.peachack.exchanger.dto.CurrencyRateDTO;
import by.peachack.exchanger.model.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CurrencyRateMapper {
    @Mapping(source = "currency.name", target = "currency_name")
    @Mapping(source = "currency.abbreviation", target = "currency_abbreviation")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "scale", target = "scale")
    @Mapping(source = "officialRate", target = "officialRate")
    public abstract CurrencyRateDTO currencyRateToDTO(CurrencyRate currencyRate);
}
