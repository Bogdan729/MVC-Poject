package com.project.parser;

import com.project.churchJson.RussianChurch;
import org.springframework.web.client.RestTemplate;

public class Parser {
    public static RussianChurch[] getChurches() {
        final RestTemplate restTemplate = new RestTemplate();
        RussianChurch[] churches = restTemplate.getForObject("https://apidata.mos.ru/v1/datasets/2624/rows?api_key=05dd21f471b27aecb18532c619d67261", RussianChurch[].class);

        return churches;
    }
}