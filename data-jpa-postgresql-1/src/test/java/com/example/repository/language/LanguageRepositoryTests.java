package com.example.repository.language;

import com.example.model.language.AbstractLanguageTests;
import com.example.model.language.Language;
import com.example.model.language.Language.Code;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageRepositoryTests extends AbstractLanguageTests {

    //Create methods:

    @Override
    protected void createItem(Language item) {
        getRepository().save(item);
    }

    @Override
    protected void createItemWithInvalidItem(Language item) {
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
    protected List<Language> getAllItems() {
        Iterable<Language> iterable = getRepository().findAll();
        List<Language> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Language> getItemById(Long id) {
        return getRepository().findById(id);
    }

    @Override
    protected Optional<Language> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Language> getItemsByName(String value) {
        return getItemListFromItemIterable(getRepository().findByName(value));
    }

    @Override
    protected List<Language> getItemsByCode(Code value) {
        return getItemListFromItemIterable(getRepository().findByCode(value));
    }
    //Update methods:

    @Override
    protected void updateItem(Language item) {
        Optional<Language> existingItem = getItemById(item.getId());
        if(existingItem.isPresent()) {
            getRepository().save(item);
        }
    }

    @Override
    protected void updateItemForNonExistingItem(Language item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Language item) {
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
    protected void deleteItemById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    protected void deleteItemByIdForNonExistingId(Long id) {
        Optional<Language> existingItem = getItemById(id);
        if(existingItem.isPresent()) {
            getRepository().deleteById(id);
        }
    }
}
