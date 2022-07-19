package com.zkz.webclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkz.webclient.configuration.AppProperties;
import com.zkz.webclient.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Jason Zhuang on 19/07/22.
 */
@Slf4j
@Service
public class AppService {

    private static String baseURL = "https://api.sendinblue.com/v3/smtp/email";

    private final AppProperties appProperties;
    //inject RestTemplate, so we can call another api endpoints which are outside this service
    private final RestTemplate restTemplate;

    public AppService(AppProperties appProperties, RestTemplate restTemplate) {
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
    }

    // using RestTemplate to call the other endpoint which is outside this service,
    // communicating with other microservice
    public ProductDTO callOuterEndpointByRestTemplate(String userId) {
        String myUrl = "https://localhost:8093/users" + userId;
        ProductDTO obj = restTemplate.getForObject(myUrl, ProductDTO.class);
        return obj;
    }

    public List<Object> callGetByWebClient() {
        String uri = "http://***.com.au:8082/learning/usr/s";
        WebClient.Builder webClient = WebClient.builder().defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        WebClient.ResponseSpec responseSpec = webClient.build().get().uri(uri).retrieve();

        List<Object> objs = responseSpec.bodyToMono(List.class).block();

        return objs;
    }

    public Map<String,String> callPostByWebClient() {
        //Creating a WebClient Instance
        WebClient webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("api-key", appProperties.getCustomizedEmailServiceApiKey())
                .build();

        Mono<Map> result = webClient.post()
                .body(Mono.just(buildEmailDTO()), EmailDTO.class)
                .retrieve()
                .bodyToMono(Map.class);

        return result.block();
    }
    private EmailDTO buildEmailDTO(){
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSender(new Sender("sender sender", "sender@hotmail.com"));

        List<Receiver> receivers = new ArrayList<>();
        receivers.add(new Receiver("Jason Receiver", "Jason.Receiver@gmail.com"));
        emailDTO.setTo(receivers);

        emailDTO.setSubject("Account Confirmation");
        emailDTO.setTemplateId(1);

        Params params = new Params();
        params.setAppName("MyAppName");
        params.setLogoURL("https://static1.s123-cdn-static-a.com/uploads/3139464/400_filter_nobg_627b523d68ca5.png");
        params.setVerifyAddress("https://www.google.com.au");
        emailDTO.setParams(params);

        return emailDTO;
    }
    private String buildEmailJSON(EmailDTO emailDTO) {
        String jsonStr = "";
        // Creating Object of ObjectMapper define in Jackson API
        ObjectMapper Obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            jsonStr = Obj.writeValueAsString(emailDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    private Consumer<HttpHeaders> createHttpHeaders() {
        return httpHeaders -> {
            httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add("api-key", appProperties.getCustomizedEmailServiceApiKey());
        };
    }


}
