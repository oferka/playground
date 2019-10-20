package com.example.controller.api.rest.keyword;

import com.example.model.keyword.AbstractKeywordTests;
import com.example.model.keyword.Keyword;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.controller.api.rest.keyword.KeywordController.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeywordSystemTests extends AbstractKeywordTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    //Create methods:

    @Override
    protected void createItem(Keyword item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Keyword> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<URI> responseEntity = testRestTemplate.exchange(requestEntity, URI.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Override
    protected void createItemWithInvalidItem(Keyword item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Keyword> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

    //Read methods:

    @Override
    protected List<Keyword> getAllItems() {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword[]> responseEntity = testRestTemplate.exchange(requestEntity, Keyword[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Keyword[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected Optional<Keyword> getItemById(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword> responseEntity = testRestTemplate.exchange(requestEntity, Keyword.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Keyword item = responseEntity.getBody();
        assertNotNull(item);
        return Optional.of(item);
    }

    @Override
    protected Optional<Keyword> getItemByIdForNonExistingId(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword> responseEntity = testRestTemplate.exchange(requestEntity, Keyword.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        return Optional.empty();
    }

    @Override
    protected List<Keyword> getItemsByText(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(KEYWORD_TEXT_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_TEXT_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword[]> responseEntity = testRestTemplate.exchange(requestEntity, Keyword[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Keyword[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Keyword> getItemsByDateDefined(LocalDate value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(KEYWORD_DATE_DEFINED_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_DATE_DEFINED_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword[]> responseEntity = testRestTemplate.exchange(requestEntity, Keyword[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Keyword[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Keyword> getItemsByLanguageName(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(KEYWORD_LANGUAGE_NAME_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_LANGUAGE_NAME_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Keyword[]> responseEntity = testRestTemplate.exchange(requestEntity, Keyword[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Keyword[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    //Update methods:

    @Override
    protected void updateItem(Keyword item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Keyword> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemForNonExistingItem(Keyword item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Keyword> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemWithInvalidItem(Keyword item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Keyword> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

    //Helper methods:

    private UriComponents generateUriComponents() {
        return generateUriComponents(EMPTY, null);
    }

    private UriComponents generateUriComponents(String pathSuffix) {
        return generateUriComponents(pathSuffix, null);
    }

    private UriComponents generateUriComponents(String pathSuffix, MultiValueMap<String, String> params) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/" + KEYWORDS_PATH + pathSuffix)
                .queryParams(params)
                .build();
    }
}
