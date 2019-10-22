package com.example.service.keyword;

import com.example.model.keyword.AbstractKeywordTests;
import com.example.model.keyword.Keyword;
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
public class KeywordServiceTests extends AbstractKeywordTests {

    @Autowired
    private KeywordService service;

    //Create methods:

    @Override
    protected Keyword createItem(Keyword item) {
        return service.createItem(item);
    }

    @Override
    protected void createItemWithInvalidItem(Keyword item) {
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
    protected List<Keyword> getAllItems() {
        Iterable<Keyword> itemIterable = service.findAllItems();
        List<Keyword> result = new ArrayList<>();
        itemIterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Keyword> getItemById(Long id) {
        return service.findItemById(id);
    }

    @Override
    protected Optional<Keyword> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Keyword> getItemsByText(String value) {
        return getItemListFromItemIterable(service.findItemsByText(value));
    }

    @Override
    protected List<Keyword> getItemsByDateDefined(LocalDate value) {
        return getItemListFromItemIterable(service.findItemsByDateDefined(value));
    }

    @Override
    protected List<Keyword> getItemsByLanguageName(String value) {
        return getItemListFromItemIterable(service.findItemsByLanguageName(value));
    }

    //Update methods:

    @Override
    protected void updateItem(Keyword item) {
        service.updateItem(item);
    }

    @Override
    protected void updateItemForNonExistingItem(Keyword item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Keyword item) {
        try {
            service.updateItem(item);
            fail();
        }
        catch (ConstraintViolationException e) {
            assertFalse(e.getConstraintViolations().isEmpty());
        }
    }
}
