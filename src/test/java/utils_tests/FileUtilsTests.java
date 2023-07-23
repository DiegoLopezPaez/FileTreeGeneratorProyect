package utils_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilsTests {
    @Test
    void pathWithoutFileNameTest() throws IOException {
        File file = new File("path/without/file/name/test");
        File fileWithExpectedPath = new File("path/without/file/name");
        Assertions.assertEquals(fileWithExpectedPath.getCanonicalPath(), FileUtils.getFilePathWithoutFileName(file));
    }

    @Test
    void fileNameTest(){
        File file_1 = new File("Path/test");
        File file_2 = new File("Path/some/test");
        Assertions.assertAll(
                ()-> Assertions.assertEquals("test", FileUtils.getFileName(file_1)),
                ()-> Assertions.assertEquals(FileUtils.getFileName(file_1), FileUtils.getFileName(file_2))
        );
    }
    @Test
    void validFileNamesTest(){
        String ex1 = "file";
        String ex2 = "Afil892";
        String ex3 = "12aSd32";
        String ex4 = "MyArchivo jaja32";
        String ex5 = "file.hol";
        String ex6 = "dkls ls34 fj .pop";
        String ex7 = "asdf 21.pii";
        String ex8 = "pls.rer";
        String ex9 = "a";
        Assertions.assertAll(
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex1)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex2)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex3)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex4)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex5)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex6)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex7)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex8)),
                ()-> Assertions.assertTrue(FileUtils.isValidFileName(ex9))
        );
    }
    @Test
    void invalidFileNamesTest(){
        String ex1 = "file.asdfasdfasdfasdf";
        String ex2 = "Afil892.123";
        String ex5 = "fi/le.hol";
        String ex6 = "dkls\" ls34 fj .po/p";
        String ex7 = "COM1";
        String ex8 = "CLOCK$";
        String ex9 = "LPT20";
        String ex10 = "file\\name.rar";
        Assertions.assertAll(
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex1)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex2)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex5)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex6)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex7)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex8)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex9)),
                ()-> Assertions.assertFalse(FileUtils.isValidFileName(ex10))
        );
    }
}
