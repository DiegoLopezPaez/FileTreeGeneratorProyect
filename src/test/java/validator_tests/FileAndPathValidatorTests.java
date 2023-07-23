package validator_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validators.FileAndPathValidator;

import java.io.File;

public class FileAndPathValidatorTests {
    static String pathAParent;
    static String pathAChild;
    static String invalidPathAChild_1;
    static  String invalidPathAChild_2;
    @BeforeAll
    static void setup(){
        pathAParent = "path/A/Parent";
        pathAChild = "path/A/Parent/Child";
        invalidPathAChild_1 = "path/A";
        invalidPathAChild_2 = "path/A/Parent/Child/invalid";
    }
    @Test
    void nullFileTest(){
        File file = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> FileAndPathValidator.validateNullFile(file));
    }
    @Test
    void invalidChildPathForParentPath(){
        File parent = new File(pathAParent);
        File child_1 = new File(invalidPathAChild_1);
        File child_2 = new File(invalidPathAChild_2);
        Assertions.assertAll(
                ()-> Assertions.assertThrows(IllegalArgumentException.class, ()-> FileAndPathValidator.validateNewChildPath(parent, child_1)),
                ()-> Assertions.assertThrows(IllegalArgumentException.class, ()-> FileAndPathValidator.validateNewChildPath(parent, child_2))
        );
    }
    @Test
    void validParentAndChildData(){
        File parent = new File(pathAParent);
        File child = new File(pathAChild);
        Assertions.assertDoesNotThrow(()-> FileAndPathValidator.validateNewChildPath(parent, child));
    }
    @Test
    void validFIleNames(){
        Assertions.assertDoesNotThrow(()-> FileAndPathValidator.validateFileName("NODE_B"));
    }

}
