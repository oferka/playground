package com.example.controller.api.rest.keyword;

import com.example.model.keyword.AbstractKeywordTests;
import com.example.model.keyword.Keyword;
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

import static com.example.controller.api.rest.keyword.KeywordController.*;
import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeywordIntegrationTests extends AbstractKeywordTests {

    @Autowired
    private MockMvc mockMvc;

    //Create methods:

    @Override
    protected Keyword createItem(Keyword item) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/" + KEYWORDS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
        String createdItemLocation = mvcResult.getResponse().getHeader("Location");
        assert createdItemLocation != null;
        long createdItemId = Long.parseLong(createdItemLocation.substring(createdItemLocation.lastIndexOf("/") + 1));
        Optional<Keyword> createdItem = getItemById(createdItemId);
        assert createdItem.isPresent();
        return createdItem.get();
    }

    @Override
    protected void createItemWithInvalidItem(Keyword item) throws Exception {
        mockMvc.perform(post("/" + KEYWORDS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    //Read methods:

    @Override
    protected List<Keyword> getAllItems() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + KEYWORDS_PATH))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Keyword[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Keyword[].class);
        return asList(returnedItems);
    }

    @Override
    protected Optional<Keyword> getItemById(Long id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + KEYWORDS_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Keyword returnedItem = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Keyword.class);
        return Optional.of(returnedItem);
    }

    @Override
    protected Optional<Keyword> getItemByIdForNonExistingId(Long id) throws Exception {
        mockMvc.perform(get("/" + KEYWORDS_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        return Optional.empty();
    }

    @Override
    protected List<Keyword> getItemsByText(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + KEYWORDS_PATH + "/" + FIND_BY_TEXT_PATH).param(KEYWORD_TEXT_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Keyword[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Keyword[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Keyword> getItemsByDateDefined(LocalDate value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + KEYWORDS_PATH + "/" + FIND_BY_DATE_DEFINED_PATH).param(KEYWORD_DATE_DEFINED_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Keyword[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Keyword[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Keyword> getItemsByLanguageName(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + KEYWORDS_PATH + "/" + FIND_BY_LANGUAGE_NAME_PATH).param(KEYWORD_LANGUAGE_NAME_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Keyword[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Keyword[].class);
        return asList(returnedItems);
    }

    //Update methods:

    @Override
    protected void updateItem(Keyword item) throws Exception {
        mockMvc.perform(put("/" + KEYWORDS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Override
    protected void updateItemForNonExistingItem(Keyword item) throws Exception {
        mockMvc.perform(put("/" + KEYWORDS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Override
    protected void updateItemWithInvalidItem(Keyword item) throws Exception {
        mockMvc.perform(put("/" + KEYWORDS_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
