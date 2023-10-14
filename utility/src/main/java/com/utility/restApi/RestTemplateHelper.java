package com.utility.restApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.*;

@Component
public class RestTemplateHelper {

    Logger logger = LoggerFactory.getLogger("");
    @Autowired
    RestTemplate restTemplate;

    public Map<String, Object> get(StringBuilder url, Map<String, String> urlParameter) {

        logger.debug("URL " + url, " URL Parameter " + urlParameter);
        addURLParameter(url, urlParameter);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(header);
        Map<String, Object> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .getBody();
        logger.debug("Rest Template Response is " + response);
        return response;
    }

    public Map<String, Object> post(StringBuilder url, Map<String, Object> requestBody, Map<String, String> urlParameter) {
        logger.debug("URL " + url, " URL Parameter " + urlParameter);

        addURLParameter(url, urlParameter);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);
        Map<String, Object> response = restTemplate.exchange(url.toString(), HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Map<String, Object>>() {
        }).getBody();
        logger.debug("APi Response " + response);

        return response;
    }

    private String addURLParameter(StringBuilder url, Map<String, String> urlParameter) {
        if (urlParameter.isEmpty())
            return url.toString();

        Iterator<Map.Entry<String, String>> sets = urlParameter.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (sets.hasNext() && (entry = sets.next()) != null)
            url.append(entry.getKey() + "=" + entry.getValue() + (sets.hasNext() ? "&" : ""));
        return url.toString();
    }
}
