package com.example.service.book;

import com.example.model.book.AbstractBookTests;
import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests extends AbstractBookTests {

    @Autowired
    private BookService service;

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
    protected List<Book> getItemsByLanguageName(String value) {
        return getItemListFromItemIterable(service.findItemsByLanguageName(value));
    }

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
            assertTrue(!e.getConstraintViolations().isEmpty());
        }
    }

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
            assertTrue(!e.getConstraintViolations().isEmpty());
        }
    }

    @Override
    protected void deleteById(Long id) {
        service.deleteItemById(id);
    }

    @Override
    protected void deleteByIdForNonExistingId(Long id) {
        service.deleteItemById(id);
    }
}