package com.example.repository.book;

import com.example.model.book.AbstractBookTests;
import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import com.example.model.language.Language.Code;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests extends AbstractBookTests {

    //Create methods:

    @Override
    protected void createItem(Book item) {
        getRepository().save(item);
    }

    @Override
    protected void createItemWithInvalidItem(Book item) {
        try {
            getRepository().save(item);
            fail();
        }
        catch (TransactionSystemException e) {
            assertTrue(Objects.requireNonNull(e.getMessage()).contains("Could not commit JPA transaction"));
        }
    }

    //Read methods:

    @Override
    protected List<Book> getAllItems() {
        Iterable<Book> iterable = getRepository().findAll();
        List<Book> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Book> getItemById(Long id) {
        return getRepository().findById(id);
    }

    @Override
    protected Optional<Book> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Book> getItemsByTitle(String value) {
        return getItemListFromItemIterable(getRepository().findByTitle(value));
    }

    @Override
    protected List<Book> getItemsByDatePublished(LocalDate value) {
        return getItemListFromItemIterable(getRepository().findByDatePublished(value));
    }

    @Override
    protected List<Book> getItemsByNumberOfPages(Integer value) {
        return getItemListFromItemIterable(getRepository().findByNumberOfPages(value));
    }

    @Override
    protected List<Book> getItemsByFamilyFriendly(Boolean value) {
        return getItemListFromItemIterable(getRepository().findByFamilyFriendly(value));
    }

    @Override
    protected List<Book> getItemsByIsbn(String value) {
        return getItemListFromItemIterable(getRepository().findByIsbn(value));
    }

    @Override
    protected List<Book> getItemsByFormat(Format value) {
        return getItemListFromItemIterable(getRepository().findByFormat(value));
    }

    @Override
    protected List<Book> getItemsByLanguageId(Long value) {
        return getItemListFromItemIterable(getRepository().findByLanguageId(value));
    }

    @Override
    protected List<Book> getItemsByLanguageName(String value) {
        return getItemListFromItemIterable(getRepository().findByLanguageName(value));
    }

    @Override
    protected List<Book> getItemsByLanguageCode(Code value) {
        return getItemListFromItemIterable(getRepository().findByLanguageCode(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsId(Long value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsId(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsText(String value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsText(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsDateDefined(LocalDate value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsDateDefined(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageId(Long value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsLanguageId(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageName(String value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsLanguageName(value));
    }

    @Override
    protected List<Book> getItemsByKeywordsLanguageCode(Code value) {
        return getItemListFromItemIterable(getRepository().findAllByKeywordsLanguageCode(value));
    }

    //Update methods

    @Override
    protected void updateItem(Book item) {
        Optional<Book> existingItem = getItemById(item.getId());
        if(existingItem.isPresent()) {
            getRepository().save(item);
        }
    }

    @Override
    protected void updateItemForNonExistingItem(Book item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Book item) {
        try {
            getRepository().save(item);
            fail();
        }
        catch (TransactionSystemException e) {
            assertTrue(e.getMessage().contains("Could not commit JPA transaction"));
        }
    }

    //Delete methods:

    @Override
    protected boolean deleteItemById(Long id) {
        getRepository().deleteById(id);
        return true;
    }

    @Override
    protected void deleteItemByIdForNonExistingId(Long id) {
        Optional<Book> existingItem = getItemById(id);
        if(existingItem.isPresent()) {
            getRepository().deleteById(id);
        }
    }
}
