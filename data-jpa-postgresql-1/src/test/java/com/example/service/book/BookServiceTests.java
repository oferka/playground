package com.example.service.book;

import com.example.model.book.AbstractBookTests;
import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import com.example.model.language.Language.Code;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests extends AbstractBookTests {

    @Autowired
    private BookService service;

    //Create methods:

    @Override
    protected void createItem(Book item) {
        service.createItem(item);
    }

    @Override
    protected void createItemWithInvalidItem(Book item) {
        try {
            service.createItem(item);
            fail();
        }
        catch (ConstraintViolationException e) {
            assertFalse(e.getConstraintViolations().isEmpty());
        }
    }

    //Read methods:

    @Override
    protected List<Book> getAllItems() {
        Iterable<Book> itemIterable = service.findAllItems();
        List<Book> result = new ArrayList<>();
        itemIterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Book> getItemById(Long id) {
        return service.findItemById(id);
    }

    @Override
    protected Optional<Book> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Book> getItemsByTitle(String value) {
        return getItemListFromItemIterable(service.findItemsByTitle(value));
    }

    @Override
    protected List<Book> getItemsByDatePublished(LocalDate value) {
        return getItemListFromItemIterable(service.findItemsByDatePublished(value));
    }

    @Override
    protected List<Book> getItemsByNumberOfPages(Integer value) {
        return getItemListFromItemIterable(service.findItemsByNumberOfPages(value));
    }

    @Override
    protected List<Book> getItemsByFamilyFriendly(Boolean value) {
        return getItemListFromItemIterable(service.findItemsByFamilyFriendly(value));
    }

    @Override
    protected List<Book> getItemsByIsbn(String value) {
        return getItemListFromItemIterable(service.findItemsByIsbn(value));
    }

    @Override
    protected List<Book> getItemsByFormat(Format value) {
        return getItemListFromItemIterable(service.findItemsByFormat(value));
    }

    @Override
    protected List<Book> getItemsByLanguageId(Long value) {
        return getItemListFromItemIterable(service.findItemsByLanguageId(value));
    }

    @Override
    protected List<Book> getItemsByLanguageName(String value) {
        return getItemListFromItemIterable(service.findItemsByLanguageName(value));
    }

    @Override
    protected List<Book> getItemsByLanguageCode(Code value) {
        return getItemListFromItemIterable(service.findItemsByLanguageCode(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsId(Long value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsId(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsText(String value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsText(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsDateDefined(LocalDate value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsDateDefined(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageId(Long value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsLanguageId(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageName(String value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsLanguageName(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageCode(Code value) {
        return getItemListFromItemIterable(service.findItemsByKeywordsLanguageCode(value));
    }

    //Update methods:

    @Override
    protected void updateItem(Book item) {
        service.updateItem(item);
    }

    @Override
    protected void updateItemForNonExistingItem(Book item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Book item) {
        try {
            service.updateItem(item);
            fail();
        }
        catch (ConstraintViolationException e) {
            assertFalse(e.getConstraintViolations().isEmpty());
        }
    }

    //Delete methods:

    @Override
    protected void deleteById(Long id) {
        service.deleteItemById(id);
    }

    @Override
    protected void deleteByIdForNonExistingId(Long id) {
        service.deleteItemById(id);
    }
}