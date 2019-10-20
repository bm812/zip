package ru.zip;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.zip.ZipFile;

public class AppTest
{
    private static ZipFile zipFile;

    @BeforeClass
    public static void setup() throws IOException {
        String zipFilePath = "src/main/resources/test.zip";
        zipFile = new ZipFile(zipFilePath);
    }

    @Test
    public void Test01_zipNotEmpty() {
        assertTrue( "Zip file is not empty", zipFile.size() > 0 );
    }

    @Test
    public void Test02_matchMask() {
        String wildcard = "^[A-Z0-9]{8}-[A-Z0-9]{4}-[0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{12}.txt$";
        boolean match = Collections.list(zipFile.entries()).stream()
                .allMatch(entry -> entry.getName().matches(wildcard));
        assertTrue( "Zipped file name matches mask", match );
    }

    @Test
    public void Test03_docNotEmpty() {
        boolean match = Collections.list(zipFile.entries()).stream()
                .allMatch(entry -> entry.getSize() > 0);
        assertTrue( "Zipped file is not empty", match );
    }
}
