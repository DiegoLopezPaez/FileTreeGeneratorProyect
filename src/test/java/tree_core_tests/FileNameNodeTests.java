package tree_core_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tree_core.FileNameNode;
import tree_core.Node;

import java.util.Iterator;

public class FileNameNodeTests {
    static FileNameNode nodeA;
    static FileNameNode nodeB;
    @BeforeAll
    static void setup(){
        nodeA = new FileNameNode("NODE_A", true);
        nodeB = new FileNameNode("NODE_B");
    }
    @Test
    void parentAndChildFileNamesAndPathWithAddString(){
        nodeA.addNode("NODE_B");
        Iterator<Node<String>> iterator = nodeA.iterator();
        iterator.next();
        FileNameNode node = (FileNameNode) iterator.next();
        Assertions.assertAll(
                ()-> Assertions.assertEquals("NODE_A", nodeA.getData()),
                ()-> Assertions.assertEquals("NODE_B", node.getData())
        );
    }
    @Test
    void parentAndChildFileNamesAndPathWithAddFileNameNode(){
        nodeA.addNode(nodeB);
        Assertions.assertAll(
                ()-> Assertions.assertEquals("NODE_A", nodeA.getData()),
                ()-> Assertions.assertEquals("NODE_B", nodeB.getData())
        );
    }
    @Test
    void parentAndChildChildFileNamesAndPathWithAddFileNameNode(){
        nodeB.setAsDirectory();
        nodeB.addNode("NODE_C");
        nodeA.addNode(nodeB);
        Iterator<Node<String>> iterator = nodeA.iterator();
        iterator.next();
        iterator.next();
        FileNameNode node = (FileNameNode) iterator.next();
        Assertions.assertAll(
                ()-> Assertions.assertEquals("NODE_A", nodeA.getData()),
                ()-> Assertions.assertEquals("NODE_B", nodeB.getData()),
                ()-> Assertions.assertEquals("NODE_C", node.getData())
        );
    }
}
