package org.vitaliistf.souvenirs.filemanager;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileManagerTest {

    private static final String TEST_FILE_PATH = "test_data.ser";

    @BeforeMethod
    public void setUp() {
        // Clean up any previous test data
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @AfterMethod
    public void tearDown() {
        // Clean up after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test(dataProvider = "setInput")
    public <T> void testSaveAndLoad(Set<T> inputSet) {
        FileManager<T> fileManager = new FileManager<>(TEST_FILE_PATH);

        fileManager.saveToFile(inputSet);

        Set<T> loadedSet = fileManager.loadFromFile();

        Assert.assertEquals(loadedSet, inputSet);
    }

    @Test
    public void testLoadFromFileNonExistingFile() {
        FileManager<String> fileManager = new FileManager<>(TEST_FILE_PATH);

        Set<String> loadedSet = fileManager.loadFromFile();

        Assert.assertTrue(loadedSet.isEmpty());
    }

    @DataProvider(name = "setInput")
    public Object[][] setInput() {
        Set<String> set1 = new HashSet<>();
        set1.add("Item1");
        set1.add("Item2");

        Set<Integer> set2 = new HashSet<>();
        set2.add(10);
        set2.add(20);

        return new Object[][]{
                {set1},
                {set2}
        };
    }
}
