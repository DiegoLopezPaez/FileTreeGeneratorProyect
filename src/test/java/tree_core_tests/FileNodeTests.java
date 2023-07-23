package tree_core_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tree_core.FileNode;
import tree_core.Node;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class FileNodeTests {
    static String pathAParent;
    static String pathAChild;
    static String AChildFileName;
    static String invalidAChildFileName;
    @BeforeAll
    static void setup(){
        pathAParent = "path/A/Parent";
        pathAChild = "path/A/Parent/Child";
        AChildFileName = "Child";
        invalidAChildFileName = "Child/path";
    }
    @Test
    void nullDataInConstructor(){
        Assertions.assertThrows(IllegalArgumentException.class, ()-> new FileNode(null));
    }
    @Test
    void fileNamesWhenAddedTest() throws IOException {
        File parent = new File(pathAParent);
        File child = new File(pathAChild);

        FileNode fileNode = new FileNode(parent);
        fileNode.addNode(AChildFileName);

        Iterator<Node<File>> it = fileNode.iterator();
        String parentPath = it.next().getData().getCanonicalPath();
        String childPath = it.next().getData().getCanonicalPath();
        Assertions.assertAll(
                ()-> Assertions.assertEquals(parent.getCanonicalPath(), parentPath),
                ()-> Assertions.assertEquals(child.getCanonicalPath(), childPath)
        );
    }
    @Test
    void addWithStringTest() throws IOException {
        File file = new File(pathAParent);
        FileNode fileNode = new FileNode(file);
        Assertions.assertTrue(fileNode.addNode(AChildFileName));
    }
    @Test
    void invalidAddWithStringTest() throws IOException {
        String nullString = null;
        File file = new File(pathAParent);
        FileNode fileNode = new FileNode(file);
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, ()-> fileNode.addNode(invalidAChildFileName)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, ()-> fileNode.addNode("")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, ()-> fileNode.addNode(nullString))
        );
    }
    @Test
    void addWithFIleTest() throws IOException {
        File file = new File(pathAParent);
        FileNode fileNode = new FileNode(file);
        Assertions.assertTrue(fileNode.addNode(new File(pathAChild)));
    }
    @Test
    void invalidAddWithFileTest(){
        File file = new File(pathAParent);
        FileNode fileNode = new FileNode(file);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> fileNode.addNode(new File(AChildFileName)));
    }
    @Test
    void addWithNodeTest() throws IOException {
        FileNode parentFileNode = new FileNode(new File(pathAParent));
        FileNode childFileNode = new FileNode(new File(pathAChild));
        Assertions.assertTrue(parentFileNode.addNode(childFileNode));
    }
    @Test
    void invalidAddWithNodeTest(){
        FileNode parentFileNode = new FileNode(new File(pathAParent));
        FileNode childFileNode = new FileNode(new File(AChildFileName));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> parentFileNode.addNode(childFileNode));
    }
}
