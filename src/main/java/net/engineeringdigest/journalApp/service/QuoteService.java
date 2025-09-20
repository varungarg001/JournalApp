package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.quoteresponse.QuoteResponsePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;


@Component
public class QuoteService {

    @Autowired
    private RestTemplate restTemplate;

    private static String quoteApi="https://api.api-ninjas.com/v1/quotes";

    @Value("${quote.api.key}")
    private String apiKey;

    public List<QuoteResponsePOJO> getQuote(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("X-Api-Key",apiKey);
        ResponseEntity<List<QuoteResponsePOJO>> response = restTemplate.exchange(quoteApi, HttpMethod.GET, new HttpEntity<>(httpHeaders), new ParameterizedTypeReference<List<QuoteResponsePOJO>>(){
        });
        List<QuoteResponsePOJO>responseBody=response.getBody();
        return responseBody;


    }
}
