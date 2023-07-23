package utils_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tree_core.FileNameNode;
import utils.FileNameNodeUtils;

public class FileNameNodeUtilsTests {
    static FileNameNode nodeA;
    static FileNameNode nodeB;
    static FileNameNode nodeC;
    static String rootDir;
    @BeforeAll
    static void setup(){
        rootDir = "root";
        nodeA = new FileNameNode("NODE_A", true);
        nodeB = new FileNameNode("NODE_B", true);
        nodeC = new FileNameNode("NODE_C");
        nodeA.addNode(nodeB);
        nodeB.addNode(nodeC);
    }
    @Test
    void pathForNodeTest(){
        String expectedPathForANode = rootDir + "\\NODE_A";
        String expectedPathForBNode = rootDir + "\\NODE_A\\NODE_B";
        String expectedPathForCNode = rootDir + "\\NODE_A\\NODE_B\\NODE_C";
        Assertions.assertAll(
                ()-> Assertions.assertEquals(expectedPathForANode, FileNameNodeUtils.getPathForNode(rootDir,nodeA)),
                ()-> Assertions.assertEquals(expectedPathForBNode, FileNameNodeUtils.getPathForNode(rootDir,nodeB)),
                ()-> Assertions.assertEquals(expectedPathForCNode, FileNameNodeUtils.getPathForNode(rootDir,nodeC))
        );
    }
    @Test
    void invalidDataTest(){
        Assertions.assertAll(
                ()-> Assertions.assertThrows(IllegalArgumentException.class, ()-> FileNameNodeUtils.getPathForNode(null, nodeA)),
                ()-> Assertions.assertThrows(IllegalArgumentException.class, ()-> FileNameNodeUtils.getPathForNode(rootDir, null))
        );
    }

}
