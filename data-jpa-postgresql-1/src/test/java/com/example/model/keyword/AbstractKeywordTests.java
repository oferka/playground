package com.example.model.keyword;

import com.example.model.AbstractModelTests;
import com.example.model.language.Language;
import com.example.repository.keyword.KeywordRepository;
import lombok.Getter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.model.book.Book.TITLE_MAX_LENGTH;
import static com.example.model.book.Book.TITLE_MIN_LENGTH;
import static java.time.LocalDate.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.*;

@Getter
public abstract class AbstractKeywordTests extends AbstractModelTests<Keyword> {

    @Autowired
    private KeywordRepository repository;

    //Create tests:

    @Test
    public void shouldFailValidationForCreateItemWithNullText() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTextValue(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankText() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTextValue(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTextShorterThanMin() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTextValue(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTextLongerThanMax() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTextValue(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullDateCreated() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDateDefinedValue(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithFutureDateCreated() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDateDefinedValue(now().plusDays(nextInt(1,100))));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullLanguage() throws Exception {
        Language invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidLanguageValue(invalidValue));
    }

    //Read tests:

    @Test
    public void shouldFindItemsByText() throws Exception {
        String existingItemValue = getExistingItemText();
        List<Keyword> items = getItemsByText(existingItemValue);
        verifyItemsTextAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingText() throws Exception {
        String nonExistingItemValue = getNonExistingItemText();
        List<Keyword> items = getItemsByText(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByDateDefined() throws Exception {
        LocalDate existingItemValue = getExistingItemDateDefined();
        List<Keyword> items = getItemsByDateDefined(existingItemValue);
        verifyItemsDateDefinedAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingDateDefined() throws Exception {
        LocalDate nonExistingItemValue = getNonExistingItemDateDefined();
        List<Keyword> items = getItemsByDateDefined(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByLanguageName() throws Exception {
        String existingItemValue = getExistingItemLanguageName();
        List<Keyword> items = getItemsByLanguageName(existingItemValue);
        verifyItemsLanguageNameAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingLanguageName() throws Exception {
        String nonExistingItemValue = getNonExistingItemLanguageName();
        List<Keyword> items = getItemsByLanguageName(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    //Update tests:

    @Test
    public void shouldFailValidationForUpdateItemWithNullText() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTextValue(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankText() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTextValue(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTextShorterThanMin() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTextValue(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTextLongerThanMax() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTextValue(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullDateDefined() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDateDefinedValue(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithFutureDateDefined() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDateDefinedValue(now().plusDays(nextInt(1, 100))));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullLanguage() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidLanguageValue(null));
    }

    //Overridden methods:

    @Override
    protected Keyword getItemToBeCreated() {
        Keyword result = getExistingItem();
        result.setId(null);
        return result;
    }

    @Override
    protected Keyword getUpdatedItem(Keyword item) {
        String updateValue = getUpdatedItemAttributeValue(item.getText());
        item.setText(updateValue);
        return item;
    }

    //Abstract methods:

    protected abstract List<Keyword> getItemsByText(String value) throws Exception;

    protected abstract List<Keyword> getItemsByDateDefined(LocalDate value) throws Exception;

    protected abstract List<Keyword> getItemsByLanguageName(String value) throws Exception;

    //Helper methods:

    private Keyword getItemWithInvalidTextValue(String value) {
        Keyword result = getExistingItem();
        result.setText(value);
        return result;
    }

    private Keyword getItemWithInvalidDateDefinedValue(LocalDate value) {
        Keyword result = getExistingItem();
        result.setDateDefined(value);
        return result;
    }

    private Keyword getItemWithInvalidLanguageValue(Language value) {
        Keyword result = getExistingItem();
        result.setLanguage(value);
        return result;
    }

    private void verifyItemsTextAsSpecified(List<Keyword> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Keyword item : items) {
            assertEquals(expectedValue, item.getText());
        }
    }

    private void verifyItemsDateDefinedAsSpecified(List<Keyword> items, LocalDate expectedValue) {
        assertFalse(items.isEmpty());
        for (Keyword item : items) {
            assertEquals(expectedValue, item.getDateDefined());
        }
    }

    private void verifyItemsLanguageNameAsSpecified(List<Keyword> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Keyword item : items) {
            assertEquals(expectedValue, item.getLanguage().getName());
        }
    }

    private String getExistingItemText() {
        return getExistingItem().getText();
    }

    private LocalDate getExistingItemDateDefined() {
        return getExistingItem().getDateDefined();
    }

    private Language getExistingItemLanguage() {
        return getExistingItem().getLanguage();
    }

    private String getExistingItemLanguageName() {
        return getExistingItemLanguage().getName();
    }

    private String getNonExistingItemText() throws Exception {
        String text = null;
        while ((text == null) || (!getItemsByText(text).isEmpty())) {
            text = randomAlphabetic(16);
        }
        return text;
    }

    private LocalDate getNonExistingItemDateDefined() throws Exception {
        LocalDate result = null;
        while ((result == null) || (!getItemsByDateDefined(result).isEmpty())) {
            result = LocalDate.now().minusDays(nextInt(0, 10000));
        }
        return result;
    }

    private String getNonExistingItemLanguageName() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByLanguageName(result).isEmpty())) {
            result = randomAlphabetic(6);
        }
        return result;
    }
}
