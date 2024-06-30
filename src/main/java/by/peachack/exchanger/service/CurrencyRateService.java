package by.peachack.exchanger.service;

import by.peachack.exchanger.client.CurrencyRateApiClient;
import by.peachack.exchanger.model.CurrencyRate;
import by.peachack.exchanger.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateApiClient currencyRateApiClient;
    private final CurrencyRateRepository currencyRateRepository;

    public List<CurrencyRate> loadCurrency(LocalDate date) {
        List<CurrencyRate> rateList = currencyRateApiClient.getCurrenciesRate(date);
        return currencyRateRepository.saveAll(rateList);
    }

    public CurrencyRate getRate(LocalDate date, Integer code) {
        return currencyRateRepository.findByDateAndCurrency_Code(date, code)
                        .orElseThrow(() -> new NoSuchElementException(
                                String.format("Not found rate for currency with code %d on date %s", code, date.format(DateTimeFormatter.ISO_DATE))));
    }
}
