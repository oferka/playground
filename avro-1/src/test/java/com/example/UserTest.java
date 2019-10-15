package com.example;

import com.github.javafaker.Faker;
import example.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void testCreateUsingConstructor() {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        // Leave favorite color null
        assertNotNull(user1);

        // Alternate constructor
        User user2 = new User("Ben", 7, "red");
        assertNotNull(user2);
    }

    @Test
    public void testCreateUsingBuilder() {
        User user = User.newBuilder()
                .setName("Ofer Karp")
                .setFavoriteNumber(7)
                .setFavoriteColor("Blue")
                .build();
        assertNotNull(user);
    }

    @Test
    public void testSerializingAndDeserializing() throws IOException {
        List<User> users = getRandomUsers(10);

        // Serialize users to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        File file = new File("target/users_data.avro");
        Schema schema = users.get(0).getSchema();
        dataFileWriter.create(schema, file);
        for(User user : users) {
            dataFileWriter.append(user);
        }
        dataFileWriter.close();


        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
        // Reuse user object by passing it to next(). This saves us from allocating and garbage collecting many objects for files with many items.
            user = dataFileReader.next(user);
            System.out.println(user);
            assertTrue(users.contains(user));
        }
    }

    @Test
    public void testSerializingAndDeserializingWithoutCodeGeneration() throws IOException {
        Schema schema = new Schema.Parser().parse(new File("src/main/avro/user.avsc"));

        GenericRecord user1GenericRecord = new GenericData.Record(schema);
        user1GenericRecord.put("name", "Alyssa");
        user1GenericRecord.put("favorite_number", 256);
        // Leave favorite color null
        GenericRecord user2GenericRecord = new GenericData.Record(schema);
        user2GenericRecord.put("name", "Ben");
        user2GenericRecord.put("favorite_number", 7);
        user2GenericRecord.put("favorite_color", "red");
        List<GenericRecord> genericRecords = asList(user1GenericRecord, user2GenericRecord);

        // Serialize user1 and user2 to disk
        File file = new File("target/users_data_2.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        for(GenericRecord genericRecord : genericRecords) {
            dataFileWriter.append(genericRecord);
        }
        dataFileWriter.close();

        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord userGenericRedord = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from allocating and garbage collecting many objects for files with many items.
            userGenericRedord = dataFileReader.next(userGenericRedord);
            System.out.println(userGenericRedord);
            assertTrue(genericRecords.contains(userGenericRedord));
        }
    }

    private List<User> getRandomUsers(int usersCount) {
        List<User> result = new ArrayList<>();
        for(int i=0; i<usersCount; i++) {
            result.add(getRandomUser());
        }
        return result;
    }

    private User getRandomUser() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        int favoriteNumber = faker.number().numberBetween(0,100);
        String favoriteColor = faker.color().name();
        User user = User.newBuilder()
                .setName(name)
                .setFavoriteNumber(favoriteNumber)
                .setFavoriteColor(favoriteColor)
                .build();
        return user;
    }
}
