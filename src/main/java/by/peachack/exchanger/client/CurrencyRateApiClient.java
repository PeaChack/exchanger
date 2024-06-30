package by.peachack.exchanger.client;

import by.peachack.exchanger.model.Currency;
import by.peachack.exchanger.model.CurrencyRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateApiClient {
    Optional<CurrencyRate> getCurrencyRate(LocalDate date, Integer code);
    List<CurrencyRate> getCurrenciesRate(LocalDate date);
    List<Currency> getCurrencies();
}
