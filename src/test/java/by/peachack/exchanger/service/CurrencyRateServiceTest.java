package by.peachack.exchanger.service;

import by.peachack.exchanger.client.CurrencyRateApiClient;
import by.peachack.exchanger.model.Currency;
import by.peachack.exchanger.model.CurrencyRate;
import by.peachack.exchanger.repository.CurrencyRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class CurrencyRateServiceTest {

    @Mock
    private CurrencyRateApiClient currencyRateApiClient;

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private CurrencyRateService currencyRateService;

    private LocalDate testDate;
    private CurrencyRate testCurrencyRate;
    private List<CurrencyRate> testCurrencyRateList;

    @BeforeEach
    void setUp() {
        testDate = LocalDate.of(2023, 6, 30);
        testCurrencyRate = new CurrencyRate();
        testCurrencyRate.setCurrency(new Currency(123, "USD", 24, "Dollar")); // Предполагается, что класс Currency определен
        testCurrencyRate.setDate(testDate);
        testCurrencyRateList = List.of(testCurrencyRate);
    }

    @Test
    void testLoadCurrency() {
        when(currencyRateApiClient.getCurrenciesRate(testDate)).thenReturn(testCurrencyRateList);
        when(currencyRateRepository.saveAll(any())).thenReturn(testCurrencyRateList);

        List<CurrencyRate> result = currencyRateService.loadCurrency(testDate);

        assertEquals(testCurrencyRateList, result);
        verify(currencyRateApiClient).getCurrenciesRate(testDate);
        verify(currencyRateRepository).saveAll(testCurrencyRateList);
    }

    @Test
    void testGetRate() {
        when(currencyRateRepository.findByDateAndCurrency_Code(testDate, 123)).thenReturn(Optional.of(testCurrencyRate));

        CurrencyRate result = currencyRateService.getRate(testDate, 123);

        assertEquals(testCurrencyRate, result);
        verify(currencyRateRepository).findByDateAndCurrency_Code(testDate, 123);
    }

    @Test
    void testGetRateNotFound() {
        when(currencyRateRepository.findByDateAndCurrency_Code(testDate, 123)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            currencyRateService.getRate(testDate, 123);
        });

        assertEquals("Not found rate for currency with code 123 on date 2023-06-30", exception.getMessage());
        verify(currencyRateRepository).findByDateAndCurrency_Code(testDate, 123);
    }
}