package tree_core_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tree_core.Node;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

//consejo: Ctrl + shift + f10 ejecuta un solo test (el que este en el cursor)
public class NodeTests {
    static Node<String> nodeA;
    static Node<String> nodeB;
    @BeforeAll
    static void setup(){
        nodeA = new Node<>("A");
        nodeB = new Node<>("B");
    }
    @Test
    void addingNullNodeTest() throws IOException {
        Node<String> node = new Node<>("Node");
        Node<String> nullNode = null;
        Assertions.assertFalse(node.addNode(nullNode));
    }
    @Test
    void nodeLevelsTest() throws IOException {
        nodeA.addNode(nodeB);
        int nodeALevel = nodeA.getLevel();
        int nodeBLevel = nodeB.getLevel();
        Assertions.assertAll(
                ()-> Assertions.assertTrue(nodeALevel < nodeBLevel),
                ()-> Assertions.assertEquals(0, nodeALevel),
                ()-> Assertions.assertEquals(1, nodeBLevel)
        );
    }
    @Test
    void leafAndRootTest() throws IOException {
        nodeA.addNode(nodeB);
        Assertions.assertAll(
                ()-> Assertions.assertTrue(nodeA.isRoot()),
                ()-> Assertions.assertFalse(nodeA.isLeaf()),
                ()-> Assertions.assertTrue(nodeB.isLeaf()),
                ()-> Assertions.assertFalse(nodeB.isRoot())
        );
    }
}
