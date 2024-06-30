package by.peachack.exchanger.client;

import by.peachack.exchanger.dto.nbrb.NbrbCurrencyDTO;
import by.peachack.exchanger.dto.nbrb.NbrbCurrencyRateDTO;
import by.peachack.exchanger.mapper.nbrb.NbrbCurrencyMapper;
import by.peachack.exchanger.model.Currency;
import by.peachack.exchanger.model.CurrencyRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NbrbRestClient implements CurrencyRateApiClient {
    private final RestClient restClient;
    private final NbrbCurrencyMapper currencyMapper;

    public NbrbRestClient(@Value("${bank.api.urls.nbrb}") String baseUrl, NbrbCurrencyMapper currencyMapper) {
        restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.currencyMapper = currencyMapper;
    }

    @Override
    public Optional<CurrencyRate> getCurrencyRate(LocalDate date, Integer code) {
        NbrbCurrencyRateDTO response = restClient.get()
                .uri("/rates/{code}?ondate={date}", code, date)
                .retrieve()
                .body(NbrbCurrencyRateDTO.class);
        return Optional.ofNullable(currencyMapper.NbrbRateToRate(response));
    }

    @Override
    public List<CurrencyRate> getCurrenciesRate(LocalDate date) {
        List<NbrbCurrencyRateDTO> responseList = restClient.get()
                .uri("/rates?ondate={date}&periodicity=0", date)
                .retrieve()
                .body(new ParameterizedTypeReference<List<NbrbCurrencyRateDTO>>() {
                });
        if (responseList != null) {
            return responseList.stream()
                    .map(currencyMapper::NbrbRateToRate)
                    .collect(Collectors.toList());
        } else
            return new ArrayList<>();
    }

    @Override
    public List<Currency> getCurrencies() {
        List<NbrbCurrencyDTO> responseList = restClient.get()
                .uri("/currencies")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (responseList != null) {
            return responseList.stream()
                    .map(currencyMapper::NbrbCurrencyToCurrency)
                    .distinct()
                    .toList();
        } else
            return new ArrayList<>();
    }
}
