package com.example.controller.api.rest.language;

import com.example.model.language.AbstractLanguageTests;
import com.example.model.language.Language;
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

import java.util.List;
import java.util.Optional;

import static com.example.controller.api.rest.language.LanguageController.*;
import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LanguageIntegrationTests extends AbstractLanguageTests {

    @Autowired
    private MockMvc mockMvc;

    //Create methods:

    @Override
    protected Language createItem(Language item) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/" + LANGUAGES_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
        String createdItemLocation = mvcResult.getResponse().getHeader("Location");
        assert createdItemLocation != null;
        long createdItemId = Long.parseLong(createdItemLocation.substring(createdItemLocation.lastIndexOf("/") + 1));
        Optional<Language> createdItem = getItemById(createdItemId);
        assert createdItem.isPresent();
        return createdItem.get();
    }

    @Override
    protected void createItemWithInvalidItem(Language item) throws Exception {
        mockMvc.perform(post("/" + LANGUAGES_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    //Read methods:

    @Override
    protected List<Language> getAllItems() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + LANGUAGES_PATH))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Language[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Language[].class);
        return asList(returnedItems);
    }

    @Override
    protected Optional<Language> getItemById(Long id) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + LANGUAGES_PATH + "/" + id))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Language returnedItem = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Language.class);
        return Optional.of(returnedItem);
    }

    @Override
    protected Optional<Language> getItemByIdForNonExistingId(Long id) throws Exception {
        mockMvc.perform(get("/" + LANGUAGES_PATH+ "/" + id))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        return Optional.empty();
    }

    @Override
    protected List<Language> getItemsByName(String value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + LANGUAGES_PATH + "/" + FIND_BY_NAME_PATH).param(LANGUAGE_NAME_PARAM_NAME, value))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Language[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Language[].class);
        return asList(returnedItems);
    }

    @Override
    protected List<Language> getItemsByCode(Code value) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/" + LANGUAGES_PATH + "/" + FIND_BY_CODE_PATH).param(LANGUAGE_CODE_PARAM_NAME, value.toString()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        Language[] returnedItems = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Language[].class);
        return asList(returnedItems);
    }

    //Update methods:

    @Override
    protected void updateItem(Language item) throws Exception {
        mockMvc.perform(put("/" + LANGUAGES_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Override
    protected void updateItemForNonExistingItem(Language item) throws Exception {
        mockMvc.perform(put("/" + LANGUAGES_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Override
    protected void updateItemWithInvalidItem(Language item) throws Exception {
        mockMvc.perform(put("/" + LANGUAGES_PATH + "/" + item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(log())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
