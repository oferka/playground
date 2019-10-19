package com.example.model.book;

import com.example.model.AbstractModelTests;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import com.example.repository.book.BookRepository;
import com.github.javafaker.Faker;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.example.model.book.Book.*;
import static java.time.LocalDate.now;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.*;

@Getter
public abstract class AbstractBookTests extends AbstractModelTests<Book> {

    @Autowired
    private BookRepository repository;

    //Create tests:

    @Test
    public void shouldFailValidationForCreateItemWithNullTitle() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidTitle(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankTitle() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidTitle(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTitleShorterThanMin() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidTitle(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithTitleLongerThanMax() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidTitle(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullDatePublished() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidDatePublished(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithFutureDatePublished() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidDatePublished(now().plusDays(nextInt(1,100))));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullNumberOfPages() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidNumberOfPages(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNumberOfPagesBelowMin() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MIN - 1));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNumberOfPagesAboveMax() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MAX + 1));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullFamilyFriendly() throws Exception {
        Boolean invalidValue = null;
        createItemWithInvalidItem(getExistingItemWithInvalidFamilyFriendly(invalidValue));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullIsbn() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidIsbn(null));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBlankIsbn() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidIsbn(EMPTY));
    }

    @Test
    public void shouldFailValidationForCreateItemWithBadFormatIsbn() throws Exception {
        createItemWithInvalidItem(getExistingItemWithInvalidIsbn(randomAlphabetic(15)));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullFormat() throws Exception {
        Format invalidValue = null;
        createItemWithInvalidItem(getExistingItemWithInvalidFormat(invalidValue));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullLanguage() throws Exception {
        Language invalidValue = null;
        createItemWithInvalidItem(getExistingItemWithInvalidLanguage(invalidValue));
    }

    @Test
    public void shouldFailValidationForCreateItemWithNullKeywords() throws Exception {
        Set<Keyword> invalidValue = null;
        createItemWithInvalidItem(getExistingItemWithInvalidKeywords(invalidValue));
    }

    //Read tests:

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
    public void shouldFindItemsByLanguageId() throws Exception {
        Long existingItemValue = getExistingItemLanguageId();
        List<Book> items = getItemsByLanguageId(existingItemValue);
        verifyItemsLanguageIdAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingLanguageId() throws Exception {
        Long nonExistingItemValue = getNonExistingItemLanguageId();
        List<Book> items = getItemsByLanguageId(nonExistingItemValue);
        assertTrue(items.isEmpty());
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
    public void shouldFindItemsByLanguageCode() throws Exception {
        Code existingItemValue = getExistingItemLanguageCode();
        List<Book> items = getItemsByLanguageCode(existingItemValue);
        verifyItemsLanguageCodeAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldFindItemsByKeywordsId() throws Exception {
        Long existingItemValue = getExistingItemKeywordId();
        List<Book> items = getItemsByKeywordsId(existingItemValue);
        verifyItemsKeywordsIdAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingKeywordsId() throws Exception {
        Long nonExistingItemValue = getNonExistingItemKeywordsId();
        List<Book> items = getItemsByKeywordsId(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByKeywordsText() throws Exception {
        String existingItemValue = getExistingItemKeywordText();
        List<Book> items = getItemsByKeywordsText(existingItemValue);
        verifyItemsKeywordsTextAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingKeywordsText() throws Exception {
        String nonExistingItemValue = getNonExistingItemKeywordsText();
        List<Book> items = getItemsByKeywordsText(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByKeywordsDateDefined() throws Exception {
        LocalDate existingItemValue = getExistingItemKeywordDateDefined();
        List<Book> items = getItemsByKeywordsDateDefined(existingItemValue);
        verifyItemsKeywordsDateDefinedAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingKeywordsDateDefined() throws Exception {
        LocalDate nonExistingItemValue = getNonExistingItemKeywordsDateDefined();
        List<Book> items = getItemsByKeywordsDateDefined(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByKeywordsLanguageId() throws Exception {
        Long existingItemValue = getExistingItemKeywordLanguageId();
        List<Book> items = getItemsByKeywordsLanguageId(existingItemValue);
        verifyItemsKeywordsLanguageIdAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingKeywordsLanguageId() throws Exception {
        Long nonExistingItemValue = getNonExistingItemKeywordsLanguageId();
        List<Book> items = getItemsByKeywordsLanguageId(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByKeywordsLanguageName() throws Exception {
        String existingItemValue = getExistingItemKeywordLanguageName();
        List<Book> items = getItemsByKeywordsLanguageName(existingItemValue);
        verifyItemsKeywordsLanguageNameAsSpecified(items, existingItemValue);
    }

    @Test
    public void shouldReturnEmptyResultForFindItemsByNonExistingKeywordsLanguageName() throws Exception {
        String nonExistingItemValue = getNonExistingItemKeywordsLanguageName();
        List<Book> items = getItemsByKeywordsLanguageName(nonExistingItemValue);
        assertTrue(items.isEmpty());
    }

    @Test
    public void shouldFindItemsByKeywordsLanguageCode() throws Exception {
        Code existingItemValue = getExistingItemKeywordLanguageCode();
        List<Book> items = getItemsByKeywordsLanguageCode(existingItemValue);
        verifyItemsKeywordsLanguageCodeAsSpecified(items, existingItemValue);
    }

    //Update tests:

    @Test
    public void shouldFailValidationForUpdateItemWithNullTitle() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidTitle(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankTitle() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidTitle(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTitleShorterThanMin() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidTitle(randomAlphabetic(TITLE_MIN_LENGTH - 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithTitleLongerThanMax() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidTitle(randomAlphabetic(TITLE_MAX_LENGTH + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullDatePublished() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidDatePublished(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithFutureDatePublished() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidDatePublished(now().plusDays(nextInt(1, 100))));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullNumberOfPages() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidNumberOfPages(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNumberOfPagesBelowMin() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidNumberOfPages(NUMBER_OF_PAGES_MIN - 1));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNumberOfPagesAboveMax() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidTitle(randomAlphabetic(NUMBER_OF_PAGES_MAX + 1)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullFamilyFriendly() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidFamilyFriendly(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullIsbn() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidIsbn(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBlankIsbn() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidIsbn(EMPTY));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithBadFormatIsbn() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidIsbn(randomAlphabetic(15)));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullFormat() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidFormat(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullLanguage() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidLanguage(null));
    }

    @Test
    public void shouldFailValidationForUpdateItemWithNullKeywords() throws Exception {
        updateItemWithInvalidItem(getExistingItemWithInvalidKeywords(null));
    }

    //Delete tests:

    //Overridden methods:

    @Override
    protected Book getItemToBeCreated() {
        return getSampleDataLoader().getBookSampleDataLoader().getBookSampleDataProvider().getSampleItem();
    }

    @Override
    protected Book getUpdatedItem(Book item) {
        String updateValue = getUpdatedItemAttributeValue(item.getTitle());
        item.setTitle(updateValue);
        return item;
    }

    //Abstract methods:

    protected abstract List<Book> getItemsByTitle(String value) throws Exception;

    protected abstract List<Book> getItemsByDatePublished(LocalDate value) throws Exception;

    protected abstract List<Book> getItemsByNumberOfPages(Integer value) throws Exception;

    protected abstract List<Book> getItemsByFamilyFriendly(Boolean value) throws Exception;

    protected abstract List<Book> getItemsByIsbn(String value) throws Exception;

    protected abstract List<Book> getItemsByFormat(Format value) throws Exception;

    protected abstract List<Book> getItemsByLanguageId(Long id) throws Exception;

    protected abstract List<Book> getItemsByLanguageName(String value) throws Exception;

    protected abstract List<Book> getItemsByLanguageCode(Code value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsId(Long value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsText(String value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsDateDefined(LocalDate value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsLanguageId(Long value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsLanguageName(String value) throws Exception;

    protected abstract List<Book> getItemsByKeywordsLanguageCode(Code value) throws Exception;

    //Helper methods:

    private Book getExistingItemWithInvalidTitle(String value) {
        Book result = getExistingItem();
        result.setTitle(value);
        return result;
    }

    private Book getExistingItemWithInvalidDatePublished(LocalDate value) {
        Book result = getExistingItem();
        result.setDatePublished(value);
        return result;
    }

    private Book getExistingItemWithInvalidNumberOfPages(Integer value) {
        Book result = getExistingItem();
        result.setNumberOfPages(value);
        return result;
    }

    private Book getExistingItemWithInvalidFamilyFriendly(Boolean value) {
        Book result = getExistingItem();
        result.setFamilyFriendly(value);
        return result;
    }

    private Book getExistingItemWithInvalidIsbn(String value) {
        Book result = getExistingItem();
        result.setIsbn(value);
        return result;
    }

    private Book getExistingItemWithInvalidFormat(Format value) {
        Book result = getExistingItem();
        result.setFormat(value);
        return result;
    }

    private Book getExistingItemWithInvalidLanguage(Language value) {
        Book result = getExistingItem();
        result.setLanguage(value);
        return result;
    }

    private Book getExistingItemWithInvalidKeywords(Set<Keyword> value) {
        Book result = getExistingItem();
        result.setKeywords(value);
        return result;
    }

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

    private Long getExistingItemLanguageId() {
        return getExistingItemLanguage().getId();
    }

    private String getExistingItemLanguageName() {
        return getExistingItemLanguage().getName();
    }

    private Code getExistingItemLanguageCode() {
        return getExistingItemLanguage().getCode();
    }

    private Keyword getExistingItemKeyword() {
        return getExistingItem().getKeywords().iterator().next();
    }

    private Long getExistingItemKeywordId() {
        return getExistingItemKeyword().getId();
    }

    private Long getNonExistingItemKeywordsId() throws Exception {
        Long result = null;
        while ((result == null) || (!getItemsByKeywordsId(result).isEmpty())) {
            result = nextLong(1, Long.MAX_VALUE);
        }
        return result;
    }

    private String getExistingItemKeywordText() {
        return getExistingItemKeyword().getText();
    }

    private String getNonExistingItemKeywordsText() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByKeywordsText(result).isEmpty())) {
            result = randomAlphabetic(16);
        }
        return result;
    }

    private LocalDate getExistingItemKeywordDateDefined() {
        return getExistingItemKeyword().getDateDefined();
    }

    private LocalDate getNonExistingItemKeywordsDateDefined() throws Exception {
        LocalDate result = null;
        while ((result == null) || (!getItemsByKeywordsDateDefined(result).isEmpty())) {
            result = LocalDate.now().minusDays(nextInt(0, 10000));
        }
        return result;
    }

    private Long getExistingItemKeywordLanguageId() {
        return getExistingItemKeyword().getLanguage().getId();
    }

    private Long getNonExistingItemKeywordsLanguageId() throws Exception {
        Long result = null;
        while ((result == null) || (!getItemsByKeywordsLanguageId(result).isEmpty())) {
            result = nextLong(1, Long.MAX_VALUE);
        }
        return result;
    }

    private String getExistingItemKeywordLanguageName() {
        return getExistingItemKeyword().getLanguage().getName();
    }

    private String getNonExistingItemKeywordsLanguageName() throws Exception {
        String result = null;
        while ((result == null) || (!getItemsByKeywordsLanguageName(result).isEmpty())) {
            result = RandomStringUtils.randomAlphabetic(6);
        }
        return result;
    }

    private Code getExistingItemKeywordLanguageCode() {
        return getExistingItemKeyword().getLanguage().getCode();
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

    private Long getNonExistingItemLanguageId() throws Exception {
        Long result = null;
        while ((result == null) || (!getItemsByLanguageId(result).isEmpty())) {
            result = nextLong(1, Long.MAX_VALUE);
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

    private void verifyItemsLanguageIdAsSpecified(List<Book> items, Long expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getLanguage().getId());
        }
    }

    private void verifyItemsLanguageNameAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getLanguage().getName());
        }
    }

    private void verifyItemsLanguageCodeAsSpecified(List<Book> items, Code expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getLanguage().getCode());
        }
    }

    private void verifyItemsKeywordsIdAsSpecified(List<Book> items, Long expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getId());
        }
    }

    private void verifyItemsKeywordsTextAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getText());
        }
    }

    private void verifyItemsKeywordsDateDefinedAsSpecified(List<Book> items, LocalDate expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getDateDefined());
        }
    }

    private void verifyItemsKeywordsLanguageIdAsSpecified(List<Book> items, Long expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getLanguage().getId());
        }
    }

    private void verifyItemsKeywordsLanguageNameAsSpecified(List<Book> items, String expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getLanguage().getName());
        }
    }

    private void verifyItemsKeywordsLanguageCodeAsSpecified(List<Book> items, Code expectedValue) {
        assertFalse(items.isEmpty());
        for (Book item : items) {
            assertEquals(expectedValue, item.getKeywords().iterator().next().getLanguage().getCode());
        }
    }
}