package com.example.model;

import com.example.data.sample.SampleDataLoader;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.Assert.*;

@Data
public abstract class AbstractModelTests<T extends Identifiable> {

    @Autowired
    private SampleDataLoader sampleDataLoader;

    //Lifecycle methods:

    @Before
    public void deleteAllAndLoadSampleDataBeforeTests() {
        sampleDataLoader.clean();
        sampleDataLoader.load();
    }

    @After
    public void deleteAllAfterTests() {
        sampleDataLoader.clean();
    }

    //Create tests:

    @Test
    public void shouldCreateItem() throws Exception {
        T itemToBeCreated = getItemToBeCreated();
        createItem(itemToBeCreated);
        verifyItemCreation(itemToBeCreated);
    }

    //Read tests:

    @Test
    public void shouldFindAllItems() throws Exception {
        List<T> items = getAllItems();
        verifyEqualToSampleData(items);
    }

    @Test
    public void shouldFindItemById() throws Exception {
        long existingItemId = getExistingItemId();
        Optional<T> item = getItemById(existingItemId);
        assertTrue(item.isPresent());
        verifyItemIdAsSpecified(item.get(), existingItemId);
    }

    @Test
    public void shouldReturnNotFoundForFindItemByNonExistingId() throws Exception {
        long nonExistingItemId = getNonExistingItemId();
        Optional<T> item = getItemByIdForNonExistingId(nonExistingItemId);
        assertTrue(item.isEmpty());
    }

    //Update tests:

    @Test
    public void shouldUpdateItem() throws Exception {
        T existingItem = getExistingItem();
        existingItem = getUpdatedItem(existingItem);
        updateItem(existingItem);
        verifyItemUpdate(existingItem);
    }

    @Test
    public void shouldReturnNotFoundForUpdateItemWithNonExistingItem() throws Exception {
        T item = getItemToBeCreated();
        long nonExistingItemId = getNonExistingItemId();
        item.setId(nonExistingItemId);
        updateItemForNonExistingItem(item);
        verifyItemUpdateForNonExistingItem(item);
    }

    //Abstract methods:

    protected abstract List<T> getAllItems() throws Exception;

    protected abstract Optional<T> getItemById(Long id) throws Exception;

    protected abstract Optional<T> getItemByIdForNonExistingId(Long id) throws Exception;

    protected abstract T getItemToBeCreated() throws Exception;

    protected abstract void createItem(T item) throws Exception;

    protected abstract T getUpdatedItem(T item);

    protected abstract void updateItem(T item) throws Exception;

    protected abstract void updateItemForNonExistingItem(T item) throws Exception;

    protected abstract void createItemWithInvalidItem(T item) throws Exception;

    protected abstract void updateItemWithInvalidItem(T item) throws Exception;

    protected abstract JpaRepository<T, Long> getRepository();

    //Helper methods:

    protected T getExistingItem() {
        return getRepository().findAll().iterator().next();
    }

    private long getExistingItemId() {
        return getExistingItem().getId();
    }

    private boolean itemIdExists(Long id) {
        return getRepository().findById(id).isPresent();
    }

    private long getNonExistingItemId() {
        Long id = null;
        while ((id == null) || (itemIdExists(id))) {
            id = nextLong(1, Long.MAX_VALUE);
        }
        return id;
    }

    private void verifyEqualToSampleData(List<T> items) {
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    private void verifyItemIdAsSpecified(T item, Long expectedId) {
        assertEquals(expectedId, item.getId());
    }

    private void verifyItemUpdate(T item) {
        verifyItemExists(item);
    }

    private void verifyItemCreation(T item) {
        verifyItemExists(item);
    }

    private void verifyItemUpdateForNonExistingItem(T item) {
        assertTrue(getRepository().findById(item.getId()).isEmpty());
    }

    protected List<T> getItemListFromItemIterable(Iterable<T> itemIterable) {
        List<T> result = new ArrayList<>();
        itemIterable.forEach(result::add);
        return result;
    }

    private void verifyItemExists(T item) {
        assertNotNull(item);
        //todo
    }

    protected String getUpdatedItemAttributeValue(String currentValue) {
        return currentValue + " Updated";
    }
}
