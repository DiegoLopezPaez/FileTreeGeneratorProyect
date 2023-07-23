package interpreter_tests;

import interpreter.FTGInterpreter;
import interpreter.FileToFNNInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileToFNNInterpreterTests {
    static File fileWithWhitespaceInFirstLine = new File(".\\src\\test\\java\\interpreter_tests\\test_files\\firstLineWhitespaceFile");
    static File fileWithInvalidSintaxis = new File(".\\src\\test\\java\\interpreter_tests\\test_files\\invalidSintaxisFile");
    static File fileWithTwoRoots = new File(".\\src\\test\\java\\interpreter_tests\\test_files\\twoRootsFile");
    @Test
    void validateExistentFileTest(){
        File nonExistentFile = new File("nonExistent");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FileToFNNInterpreter(nonExistentFile)).printStackTrace();
    }
    @Test
    void validateFileIsNotDirectoryTest(){
        File directory = new File(".\\");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FileToFNNInterpreter(directory)).printStackTrace();
    }
    @Test
    void validateFirstLineWithoutWhitespaceTest(){
        FTGInterpreter<File> interpreter = new FileToFNNInterpreter(fileWithWhitespaceInFirstLine);
        Assertions.assertThrows(IllegalArgumentException.class, () -> interpreter.interpret()).printStackTrace();
    }
    @Test
    void validateInvalidSintaxisTest(){
        FTGInterpreter<File> interpreter = new FileToFNNInterpreter(fileWithInvalidSintaxis);
        Assertions.assertThrows(IllegalArgumentException.class, () -> interpreter.interpret()).printStackTrace();
    }
    @Test
    void validateTwoRootsTest(){
        FTGInterpreter<File> interpreter = new FileToFNNInterpreter(fileWithTwoRoots);
        Assertions.assertThrows(IllegalArgumentException.class, () -> interpreter.interpret()).printStackTrace();
    }
}
