package com.example.service.language;

import com.example.model.language.AbstractLanguageTests;
import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageServiceTests extends AbstractLanguageTests {

    @Autowired
    private LanguageService service;

    //Create methods:

    @Override
    protected Language createItem(Language item) {
        return service.createItem(item);
    }

    @Override
    protected void createItemWithInvalidItem(Language item) {
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
    protected List<Language> getAllItems() {
        Iterable<Language> itemIterable = service.findAllItems();
        List<Language> result = new ArrayList<>();
        itemIterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Language> getItemById(Long id) {
        return service.findItemById(id);
    }

    @Override
    protected Optional<Language> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Language> getItemsByName(String value) {
        return getItemListFromItemIterable(service.findItemsByName(value));
    }

    @Override
    protected List<Language> getItemsByCode(Code value) {
        return getItemListFromItemIterable(service.findItemsByCode(value));
    }

    //Update methods:

    @Override
    protected void updateItem(Language item) {
        service.updateItem(item);
    }

    @Override
    protected void updateItemForNonExistingItem(Language item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Language item) {
        try {
            service.updateItem(item);
            fail();
        }
        catch (ConstraintViolationException e) {
            assertFalse(e.getConstraintViolations().isEmpty());
        }
    }
}
