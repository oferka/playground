package com.example.model.book;

import com.example.model.AbstractModelTests;
import com.example.model.language.Language;
import com.example.repository.book.BookRepository;
import com.github.javafaker.Faker;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.model.book.Book.*;
import static java.time.LocalDate.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.*;

@Getter
public abstract class AbstractBookTests extends AbstractModelTests<Book> {

    @Autowired
    private BookRepository repository;

    @Test
    public void shouldFindItemsByTitle() throws Exception {
        String existingItemValue = getExistingItemTitle();
        List<Book> items = getItemsByTitle(existingItemValue);
        verifyItemsTitleAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingTitle() throws Exception {
        String nonExistingItemValue = getNonExistingItemTitle();
        List<Book> items = getItemsByTitle(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByDatePublished() throws Exception {
        LocalDate existingItemValue = getExistingItemDatePublished();
        List<Book> items = getItemsByDatePublished(existingItemValue);
        verifyItemsDatePublishedAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingDatePublished() throws Exception {
        LocalDate nonExistingItemValue = getNonExistingItemDatePublished();
        List<Book> items = getItemsByDatePublished(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByNumberOfPages() throws Exception {
        int existingItemValue = getExistingItemNumberOfPages();
        List<Book> items = getItemsByNumberOfPages(existingItemValue);
        verifyItemsNumberOfPagesAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingNumberOfPages() throws Exception {
        int nonExistingItemValue = getNonExistingItemNumberOfPages();
        List<Book> items = getItemsByNumberOfPages(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByFamilyFriendly() throws Exception {
        boolean existingItemValue = getExistingItemFamilyFriendly();
        List<Book> items = getItemsByFamilyFriendly(existingItemValue);
        verifyItemsFamilyFriendlyAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldFindItemsByIsbn() throws Exception {
        String existingItemValue = getExistingItemIsbn();
        List<Book> items = getItemsByIsbn(existingItemValue);
        verifyItemsIsbnAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingIsbn() throws Exception {
        String nonExistingItemValue = getNonExistingItemIsbn();
        List<Book> items = getItemsByIsbn(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByFormat() throws Exception {
        Format existingItemValue = getExistingItemFormat();
        List<Book> items = getItemsByFormat(existingItemValue);
        verifyItemsFormatAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldFindItemsByLanguageName() throws Exception {
        String existingItemValue = getExistingItemLanguageName();
        List<Book> items = getItemsByLanguageName(existingItemValue);
        verifyItemsLanguageNameAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingLanguageName() throws Exception {
        String nonExistingItemValue = getNonExistingItemLanguageName();
        List<Book> items = getItemsByLanguageName(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullTitle() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTitle(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankTitle() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTitle(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTitleShorterThanMin() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTitle(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTitleLongerThanMax() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidTitle(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullDatePublished() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDatePublished(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithFutureDatePublished() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidDatePublished(now().plusDays(nextInt(1,100))));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullNumberOfPages() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNumberOfPages(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNumberOfPagesBelowMin() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MIN - 1));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNumberOfPagesAboveMax() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MAX + 1));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullFamilyFriendly() throws Exception {
        Boolean invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidFamilyFriendly(invalidValue));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullIsbn() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidIsbn(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankIsbn() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidIsbn(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBadFormatIsbn() throws Exception {
        createItemWithInvalidItem(getItemWithInvalidIsbn(randomAlphabetic(15)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullFormat() throws Exception {
        Format invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidFormat(invalidValue));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullLanguage() throws Exception {
        Language invalidValue = null;
        createItemWithInvalidItem(getItemWithInvalidLanguage(invalidValue));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullTitle() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTitle(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankTitle() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTitle(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTitleShorterThanMin() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTitle(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTitleLongerThanMax() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTitle(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullDatePublished() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDatePublished(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithFutureDatePublished() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidDatePublished(now().plusDays(nextInt(1, 100))));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullNumberOfPages() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNumberOfPages(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNumberOfPagesBelowMin() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MIN - 1));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNumberOfPagesAboveMax() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidTitle(randomAlphabetic(NUMBER_OF_PAGES_MAX + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullFamilyFriendly() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidFamilyFriendly(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullIsbn() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidIsbn(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankIsbn() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidIsbn(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBadFormatIsbn() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidIsbn(randomAlphabetic(15)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullFormat() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidFormat(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullLanguage() throws Exception {
        updateItemWithInvalidItem(getItemWithInvalidLanguage(null));
    }

    private Book getItemWithInvalidTitle(String value) {
        Book result = getValidItem();
        result.setTitle(value);
        return result;
    }

    private Book getItemWithInvalidDatePublished(LocalDate value) {
        Book result = getValidItem();
        result.setDatePublished(value);
        return result;
    }

    private Book getItemWithInvalidNumberOfPages(Integer value) {
        Book result = getValidItem();
        result.setNumberOfPages(value);
        return result;
    }

    private Book getItemWithInvalidFamilyFriendly(Boolean value) {
        Book result = getValidItem();
        result.setFamilyFriendly(value);
        return result;
    }

    private Book getItemWithInvalidIsbn(String value) {
        Book result = getValidItem();
        result.setIsbn(value);
        return result;
    }

    private Book getItemWithInvalidFormat(Format value) {
        Book result = getValidItem();
        result.setFormat(value);
        return result;
    }

    private Book getItemWithInvalidLanguage(Language value) {
        Book result = getValidItem();
        result.setLanguage(value);
        return result;
    }

    private Book getValidItem() {
        return getSampleDataLoader().getBookSampleDataLoader().getBookSampleDataProvider().getSampleItem();
    }

    private void verifyItemsTitleAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getTitle());
        }
    }

    private void verifyItemsDatePublishedAsSpecified(List<Book> items, LocalDate expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getDatePublished());
        }
    }

    private void verifyItemsNumberOfPagesAsSpecified(List<Book> items, Integer expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getNumberOfPages());
        }
    }

    private void verifyItemsFamilyFriendlyAsSpecified(List<Book> items, Boolean expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getFamilyFriendly());
        }
    }

    private void verifyItemsIsbnAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getIsbn());
        }
    }

    private void verifyItemsFormatAsSpecified(List<Book> items, Format expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getFormat());
        }
    }

    private void verifyItemsLanguageNameAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getLanguage().getName());
        }
    }

    protected abstract List<Book> getItemsByTitle(String value) throws Exception;

    protected abstract List<Book> getItemsByDatePublished(LocalDate value) throws Exception;

    protected abstract List<Book> getItemsByNumberOfPages(Integer value) throws Exception;

    protected abstract List<Book> getItemsByFamilyFriendly(Boolean value) throws Exception;

    protected abstract List<Book> getItemsByIsbn(String value) throws Exception;

    protected abstract List<Book> getItemsByFormat(Format value) throws Exception;

    protected abstract List<Book> getItemsByLanguageName(String value) throws Exception;

    private String getExistingItemTitle() {
        return getExistingItem().getTitle();
    }

    private LocalDate getExistingItemDatePublished() {
        return getExistingItem().getDatePublished();
    }

    private Integer getExistingItemNumberOfPages() {
        return getExistingItem().getNumberOfPages();
    }

    private Boolean getExistingItemFamilyFriendly() {
        return getExistingItem().getFamilyFriendly();
    }

    private String getExistingItemIsbn() {
        return getExistingItem().getIsbn();
    }

    private Format getExistingItemFormat() {
        return getExistingItem().getFormat();
    }

    private Language getExistingItemLanguage() {
        return getExistingItem().getLanguage();
    }

    private String getExistingItemLanguageName() {
        return getExistingItemLanguage().getName();
    }

    private String getNonExistingItemTitle() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByTitle(result).isEmpty())) {
            result = randomAlphabetic(16);
        }
        return result;
    }

    private LocalDate getNonExistingItemDatePublished() throws Exception {
        LocalDate result = null;
        while ((result == null) || (!getItemsByDatePublished(result).isEmpty())) {
            result = LocalDate.now().minusDays(nextInt(0, 10000));
        }
        return result;
    }

    private Integer getNonExistingItemNumberOfPages() throws Exception {
        Integer result = null;
        while ((result == null) || (!getItemsByNumberOfPages(result).isEmpty())) {
            result = nextInt(NUMBER_OF_PAGES_MIN, NUMBER_OF_PAGES_MAX);
        }
        return result;
    }

    private String getNonExistingItemIsbn() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByIsbn(result).isEmpty())) {
            result = (nextBoolean()? (new Faker().code().isbn10(nextBoolean())) : (new Faker().code().isbn13(nextBoolean())));
        }
        return result;
    }

    private String getNonExistingItemLanguageName() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByLanguageName(result).isEmpty())) {
            result = RandomStringUtils.randomAlphabetic(6);
        }
        return result;
    }

    @Override
    protected Book getItemToBeCreated() {
        return getValidItem();
    }

    @Override
    protected Book getUpdatedItem(Book item) {
        String updateValue = getUpdatedItemAttributeValue(item.getTitle());
        item.setTitle(updateValue);
        return item;
    }
}