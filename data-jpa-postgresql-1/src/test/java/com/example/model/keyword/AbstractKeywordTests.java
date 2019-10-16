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

    @Test
    public void shouldFailValidationForCreateItemWithNullText() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidText(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankText() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidText(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTextShorterThanMin() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidText(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTextLongerThanMax() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidText(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullDateCreated() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDateDefined(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithFutureDateCreated() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDateDefined(now().plusDays(nextInt(1,100))));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullLanguage() throws Exception {
        Language invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidLanguage(invalidValue));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullText() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidText(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankText() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidText(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTextShorterThanMin() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidText(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTextLongerThanMax() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidText(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullDateDefined() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDateDefined(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithFutureDateDefined() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDateDefined(now().plusDays(nextInt(1, 100))));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullLanguage() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidLanguage(null));
    }

    private Keyword getItemWithInvalidText(String value) {
        Keyword result = getValidItem();
        result.setText(value);
        return result;
    }

    private Keyword getItemWithInvalidDateDefined(LocalDate value) {
        Keyword result = getValidItem();
        result.setDateDefined(value);
        return result;
    }

    private Keyword getItemWithInvalidLanguage(Language value) {
        Keyword result = getValidItem();
        result.setLanguage(value);
        return result;
    }

    private Keyword getValidItem() {
        return getSampleDataLoader().getKeywordSampleDataLoader().getKeywordSampleDataProvider().getSampleItem();
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

    protected abstract List<Keyword> getItemsByText(String value) throws Exception;

    protected abstract List<Keyword> getItemsByDateDefined(LocalDate value) throws Exception;

    protected abstract List<Keyword> getItemsByLanguageName(String value) throws Exception;

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

    @Override
    protected Keyword getItemToBeCreated() {
        return getValidItem();
    }

    @Override
    protected Keyword getUpdatedItem(Keyword item) {
        String updateValue = getUpdatedItemAttributeValue(item.getText());
        item.setText(updateValue);
        return item;
    }
}
