package com.example.model.language;

import com.example.model.AbstractModelTests;
import com.example.model.language.Language.Code;
import com.example.repository.language.LanguageRepository;
import lombok.Getter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.model.language.Language.NAME_MAX_LENGTH;
import static com.example.model.language.Language.NAME_MIN_LENGTH;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.*;

@Getter
public abstract class AbstractLanguageTests extends AbstractModelTests<Language> {

    @Autowired
    private LanguageRepository repository;

    //Create methods:

    @Test
    public void shouldFailValidationForCreateItemWithNullName() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNameValue(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankName() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNameValue(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNameShorterThanMin() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNameValue(randomAlphabetic(NAME_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNameLongerThanMax() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNameValue(randomAlphabetic(NAME_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullCode() throws Exception {
        Code invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidCodeValue(invalidValue));
    }

    //Read methods:

    @Test
    public void shouldFindItemsByName() throws Exception {
        String existingItemValue = getExistingItemName();
        List<Language> items = getItemsByName(existingItemValue);
        verifyItemsNameAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingName() throws Exception {
        String nonExistingItemValue = getNonExistingItemName();
        List<Language> items = getItemsByName(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByCode() throws Exception {
        Code existingItemValue = getExistingItemCode();
        List<Language> items = getItemsByCode(existingItemValue);
        verifyItemsCodeAsSpecified(items, existingItemValue);
    }

    //Update methods:

    @Test
    public void shouldFailValidationForUpdateItemWithNullName() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNameValue(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankName() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNameValue(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNameShorterThanMin() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNameValue(randomAlphabetic(NAME_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNameLongerThanMax() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNameValue(randomAlphabetic(NAME_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullCode() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidCodeValue(null));
    }

    //Overridden methods:

    @Override
    protected Language getItemToBeCreated() {
        Language result = getExistingItem();
        result.setId(null);
        return result;
    }

    @Override
    protected Language getUpdatedItem(Language item) {
        String updateValue = getUpdatedItemAttributeValue(item.getName());
        item.setName(updateValue);
        return item;
    }

    //Abstract methods:

    protected abstract List<Language> getItemsByName(String value) throws Exception;

    protected abstract List<Language> getItemsByCode(Code value) throws Exception;

    //Helper methods:

    private void verifyItemsNameAsSpecified(List<Language> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Language item : items) {
            assertEquals(expectedValue, item.getName());
        }
    }

    private void verifyItemsCodeAsSpecified(List<Language> items, Code expectedValue) {
        assertFalse(items.isEmpty());
        for (Language item : items) {
            assertEquals(expectedValue, item.getCode());
        }
    }

    private Language getItemWithInvalidNameValue(String value) {
        Language result = getExistingItem();
        result.setName(value);
        return result;
    }

    private Language getItemWithInvalidCodeValue(Code value) {
        Language result = getExistingItem();
        result.setCode(value);
        return result;
    }

    private String getExistingItemName() {
        return getExistingItem().getName();
    }

    private String getNonExistingItemName() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByName(result).isEmpty())) {
            result = randomAlphabetic(16);
        }
        return result;
    }

    private Code getExistingItemCode() {
        return getExistingItem().getCode();
    }
}
