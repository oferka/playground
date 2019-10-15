package com.example.repository.keyword;

import com.example.model.keyword.AbstractKeywordTests;
import com.example.model.keyword.Keyword;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordRepositoryTests extends AbstractKeywordTests {

    @Override
    protected List<Keyword> getAllItems() {
        Iterable<Keyword> iterable = getRepository().findAll();
        List<Keyword> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    @Override
    protected Optional<Keyword> getItemById(Long id) {
        return getRepository().findById(id);
    }

    @Override
    protected Optional<Keyword> getItemByIdForNonExistingId(Long id) {
        return getItemById(id);
    }

    @Override
    protected List<Keyword> getItemsByText(String value) {
        return getItemListFromItemIterable(getRepository().findByText(value));
    }

    @Override
    protected List<Keyword> getItemsByDateDefined(LocalDate value) {
        return getItemListFromItemIterable(getRepository().findByDateDefined(value));
    }

    @Override
    protected List<Keyword> getItemsByLanguageName(String value) {
        return getItemListFromItemIterable(getRepository().findByLanguageName(value));
    }

    @Override
    protected void createItem(Keyword item) {
        getRepository().save(item);
    }

    @Override
    protected void createItemWithInvalidItem(Keyword item) {
        try {
            getRepository().save(item);
            fail();
        }
        catch (TransactionSystemException e) {
            assertTrue(e.getMessage().contains("Could not commit JPA transaction"));
        }
    }

    @Override
    protected void updateItem(Keyword item) {
        Optional<Keyword> existingItem = getItemById(item.getId());
        if(existingItem.isPresent()) {
            getRepository().save(item);
        }
    }

    @Override
    protected void updateItemForNonExistingItem(Keyword item) {
        updateItem(item);
    }

    @Override
    protected void updateItemWithInvalidItem(Keyword item) {
        try {
            getRepository().save(item);
            fail();
        }
        catch (TransactionSystemException e) {
            assertTrue(e.getMessage().contains("Could not commit JPA transaction"));
        }
    }

    @Override
    protected void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    protected void deleteByIdForNonExistingId(Long id) {
        Optional<Keyword> existingItem = getItemById(id);
        if(existingItem.isPresent()) {
            getRepository().deleteById(id);
        }
    }
}
