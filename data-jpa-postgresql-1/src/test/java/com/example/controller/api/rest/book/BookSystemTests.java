package com.example.controller.api.rest.book;

import com.example.model.book.AbstractBookTests;
import com.example.model.book.Book;
import com.example.model.book.Book.Format;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.controller.api.rest.book.BookController.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookSystemTests extends AbstractBookTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Override
    protected List<Book> getAllItems() {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected Optional<Book> getItemById(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book> responseEntity = testRestTemplate.exchange(requestEntity, Book.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book item = responseEntity.getBody();
        assertNotNull(item);
        return Optional.of(item);
    }

    @Override
    protected Optional<Book> getItemByIdForNonExistingId(Long id) {
        UriComponents uriComponents = generateUriComponents("/" + id);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book> responseEntity = testRestTemplate.exchange(requestEntity, Book.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        return Optional.empty();
    }

    @Override
    protected List<Book> getItemsByTitle(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_TITLE_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_TITLE_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByDatePublished(LocalDate value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_DATE_PUBLISHED_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_DATE_PUBLISHED_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByNumberOfPages(Integer value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_NUMBER_OF_PAGES_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_NUMBER_OF_PAGES_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByFamilyFriendly(Boolean value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_FAMILY_FRIENDLY_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_FAMILY_FRIENDLY_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByIsbn(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_ISBN_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_ISBN_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByFormat(Format value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_FORMAT_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_FORMAT_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByLanguageId(Long value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_LANGUAGE_ID_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_LANGUAGE_ID_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByLanguageName(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_LANGUAGE_NAME_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_LANGUAGE_NAME_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByLanguageCode(Code value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_LANGUAGE_CODE_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_LANGUAGE_CODE_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsId(Long value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_ID_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_ID_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsText(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_TEXT_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_TEXT_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsDateDefined(LocalDate value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_DATE_DEFINED_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_DATE_DEFINED_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageId(Long value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_LANGUAGE_ID_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_LANGUAGE_ID_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageName(String value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_LANGUAGE_NAME_PARAM_NAME, value);
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_LANGUAGE_NAME_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageCode(Code value) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(BOOK_KEYWORDS_LANGUAGE_CODE_PARAM_NAME, value.toString());
        UriComponents uriComponents = generateUriComponents("/" + FIND_BY_KEYWORDS_LANGUAGE_CODE_PATH, params);
        URI uri = uriComponents.toUri();
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, uri);
        ResponseEntity<Book[]> responseEntity = testRestTemplate.exchange(requestEntity, Book[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Book[] items = responseEntity.getBody();
        assertNotNull(items);
        return asList(items);
    }

    @Override
    protected void createItem(Book item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Book> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<URI> responseEntity = testRestTemplate.exchange(requestEntity, URI.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Override
    protected void createItemWithInvalidItem(Book item) {
        UriComponents uriComponents = generateUriComponents();
        URI uri = uriComponents.toUri();
        RequestEntity<Book> requestEntity = new RequestEntity<>(item, HttpMethod.POST, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map<String, String> responseBody = responseEntity.getBody();
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

    @Override
    protected void updateItem(Book item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Book> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemForNonExistingItem(Book item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Book> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange(requestEntity, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Override
    protected void updateItemWithInvalidItem(Book item) {
        UriComponents uriComponents = generateUriComponents("/" + item.getId());
        URI uri = uriComponents.toUri();
        RequestEntity<Book> requestEntity = new RequestEntity<>(item, HttpMethod.PUT, uri);
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(requestEntity, Map.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map<String, String> responseBody = responseEntity.getBody();
        assertEquals(INVALID_REQUEST_ARGUMENT_REASON, responseBody.get("message"));
    }

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
                .path("/" + BOOKS_PATH + pathSuffix)
                .queryParams(params)
                .build();
    }
}
