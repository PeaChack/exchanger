package by.peachack.exchanger.config;

import by.peachack.exchanger.client.CurrencyRateApiClient;
import by.peachack.exchanger.model.Currency;
import by.peachack.exchanger.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final CurrencyRateApiClient currencyRateApiClient;
    private final CurrencyRepository currencyRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Currency> currencies = currencyRepository.findAll();
        if (currencies.isEmpty()){
            List<Currency> apiClientCurrencies = currencyRateApiClient.getCurrencies();
            currencyRepository.saveAll(apiClientCurrencies);
        }
    }
}
