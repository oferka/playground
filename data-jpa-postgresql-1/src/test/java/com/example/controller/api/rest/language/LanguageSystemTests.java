package com.example.controller.api.rest.language;

import com.example.model.language.AbstractLanguageTests;
import com.example.model.language.Language;
import com.example.model.language.Language.Code;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.controller.api.rest.language.LanguageController.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguageSystemTests extends AbstractLanguageTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    //Create methods:

    @Override
    protected void createItem(Language item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Language> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<URI> responseEntity = testRestTemplate.exchange(requestEntity, URI.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Override
    protected void createItemWithInvalidItem(Language item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Language> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

    //Read methods:

    @Override
    protected List<Language> getAllItems() {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Language[]> responseEntity = testRestTemplate.exchange(requestEntity, Language[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Language[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected Optional<Language> getItemById(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Language> responseEntity = testRestTemplate.exchange(requestEntity, Language.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Language item = responseEntity.getBody();
        assertNotNull(item);
        return Optional.of(item);
    }

    @Override
    protected Optional<Language> getItemByIdForNonExistingId(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Language> responseEntity = testRestTemplate.exchange(requestEntity, Language.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        return Optional.empty();
    }

    @Override
    protected List<Language> getItemsByName(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(LANGUAGE_NAME_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_NAME_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Language[]> responseEntity = testRestTemplate.exchange(requestEntity, Language[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Language[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Language> getItemsByCode(Code value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(LANGUAGE_CODE_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_CODE_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Language[]> responseEntity = testRestTemplate.exchange(requestEntity, Language[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Language[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    //Update methods:

    @Override
    protected void updateItem(Language item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Language> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemForNonExistingItem(Language item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Language> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemWithInvalidItem(Language item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Language> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

    //Delete methods:

    @Override
    protected void deleteById(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE, uri);
        ResponseEntity responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Override
    protected void deleteByIdForNonExistingId(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE, uri);
        ResponseEntity responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    //Helper methods

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
                .path("/" + LANGUAGES_PATH + pathSuffix)
                .queryParams(params)
                .build();
    }
}
