package com.example;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.example.CopyFileUtils.*;

public class CopyFileUtilsTest {

    private File source = new File("data/file.txt");
    private File dest = new File("data/file_copy.txt");

    @Before
    public void setup() {
        dest.delete();
    }

    @Test
    public void testCopyFileUsingFileStreams() throws IOException {
        long start = System.nanoTime();
        copyFileUsingFileStreams(source, dest);
        long end = System.nanoTime();
        System.out.println("Time taken by FileStreams Copy (in nanos) = " + (end - start));
    }

    @Test
    public void testCopyFileUsingFileChannels() throws IOException {
        long start = System.nanoTime();
        copyFileUsingFileChannels(source, dest);
        long end = System.nanoTime();
        System.out.println("Time taken by FileChannels Copy (in nanos) = " + (end - start));
    }

    @Test
    public void testCopyFileUsingApacheCommonsIO() throws IOException {
        long start = System.nanoTime();
        copyFileUsingApacheCommonsIO(source, dest);
        long end = System.nanoTime();
        System.out.println("Time taken by ApacheCommonsIO Copy (in nanos) = " + (end - start));
    }

    @Test
    public void testCopyFileUsingJava7Files() throws IOException {
        long start = System.nanoTime();
        copyFileUsingJava7Files(source, dest);
        long end = System.nanoTime();
        System.out.println("Time taken by Java7Files Copy (in nanos) = " + (end - start));
    }
}
