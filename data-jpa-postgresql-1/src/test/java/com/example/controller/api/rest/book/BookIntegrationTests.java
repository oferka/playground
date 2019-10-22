package com.example.controller.api.rest.book;

import com.example.model.book.AbstractBookTests;
import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import com.example.model.language.Language.Code;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.controller.api.rest.book.BookController.*;
import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTests extends AbstractBookTests {

    @Autowired
    private MockMvc mockMvc;

    //Create methods:

    @Override
    protected Book createItem(Book item) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/" + BOOKS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
        String createdItemLocation = mvcResult.getResponse().getHeader("Location");
        assert createdItemLocation != null;
        long createdItemId = Long.parseLong(createdItemLocation.substring(createdItemLocation.lastIndexOf("/") + 1));
        Optional<Book> createdItem = getItemById(createdItemId);
        assert createdItem.isPresent();
        return createdItem.get();
    }

    @Override
    protected void createItemWithInvalidItem(Book item) throws Exception {
        mockMvc.perform(post("/" + BOOKS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    //Read methods:

    @Override
    protected List<Book> getAllItems() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected Optional<Book> getItemById(Long id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book returnedItem = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book.class);
        return Optional.of(returnedItem);
    }

    @Override
    protected Optional<Book> getItemByIdForNonExistingId(Long id) throws Exception {
        mockMvc.perform(get("/" + BOOKS_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        return Optional.empty();
    }

    @Override
    protected List<Book> getItemsByTitle(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_TITLE_PATH).param(BOOK_TITLE_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByDatePublished(LocalDate value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_DATE_PUBLISHED_PATH).param(BOOK_DATE_PUBLISHED_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByNumberOfPages(Integer value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_NUMBER_OF_PAGES_PATH).param(BOOK_NUMBER_OF_PAGES_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByFamilyFriendly(Boolean value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_FAMILY_FRIENDLY_PATH).param(BOOK_FAMILY_FRIENDLY_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByIsbn(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_ISBN_PATH).param(BOOK_ISBN_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByFormat(Format value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_FORMAT_PATH).param(BOOK_FORMAT_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByLanguageId(Long value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_LANGUAGE_ID_PATH).param(BOOK_LANGUAGE_ID_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByLanguageName(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_LANGUAGE_NAME_PATH).param(BOOK_LANGUAGE_NAME_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByLanguageCode(Code value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_LANGUAGE_CODE_PATH).param(BOOK_LANGUAGE_CODE_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsId(Long value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_ID_PATH).param(BOOK_KEYWORDS_ID_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsText(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_TEXT_PATH).param(BOOK_KEYWORDS_TEXT_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsDateDefined(LocalDate value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_DATE_DEFINED_PATH).param(BOOK_KEYWORDS_DATE_DEFINED_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageId(Long value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_LANGUAGE_ID_PATH).param(BOOK_KEYWORDS_LANGUAGE_ID_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageName(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_LANGUAGE_NAME_PATH).param(BOOK_KEYWORDS_LANGUAGE_NAME_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageCode(Code value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + BOOKS_PATH + "/" + FIND_BY_KEYWORDS_LANGUAGE_CODE_PATH).param(BOOK_KEYWORDS_LANGUAGE_CODE_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Book[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book[].class);
        return asList(returnedItems);
    }

    //Update methods:

    @Override
    protected void updateItem(Book item) throws Exception {
        mockMvc.perform(put("/" + BOOKS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Override
    protected void updateItemForNonExistingItem(Book item) throws Exception {
        mockMvc.perform(put("/" + BOOKS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Override
    protected void updateItemWithInvalidItem(Book item) throws Exception {
        mockMvc.perform(put("/" + BOOKS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
