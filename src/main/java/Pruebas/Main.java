package Pruebas;

import ftg_core.FileTreeGenerator;
import tree_core.FileNameNode;
import tree_core.FileNode;
import tree_core.Node;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        FileNameNode nodeA = new FileNameNode("NODE_A", true);
        FileNameNode nodeB = new FileNameNode("NODE_B.as", true);
        FileNameNode nodeC = new FileNameNode("NODE_C");
        FileNameNode nodeD = new FileNameNode("NODE_D");
        FileNameNode nodeE = new FileNameNode("NODE_E", true);
        FileNameNode nodeF = new FileNameNode("NODE_F", true);
        nodeA.addNode(nodeB);
        nodeB.addNode(nodeC);
        nodeB.addNode(nodeD);
        nodeA.addNode(nodeE);
        nodeE.addNode(nodeF);
        FileTreeGenerator generator = new FileTreeGenerator("C:\\Users\\Usuario\\Desktop\\nodea", nodeA);
        generator.generate();
//        File file = new File("C:\\nodea");
//        System.out.println(file.exists());
//        FileNameNode nodeA = new FileNameNode("NODE_A", true);
//        boolean b = nodeA.addNode("NODE_B");
//        Iterator<Node<String>> it = nodeA.iterator();
//        while (it.hasNext()){
//            FileNameNode node = (FileNameNode) it.next();
//            System.out.println(node.getData() + "\n" + node.getFileName());
//        }
////        it.next();
////        FileNameNode nodeB = (FileNameNode) it.next();
//        System.out.println(nodeA.getData());
//        System.out.println(nodeA.getFileName());
//        System.out.println(nodeB.getData());
//        System.out.println(nodeB.getFileName());
//        FileUtils.isValidFileName("MyArchivo jaja32.");
//        File file = new File("C:\\Users\\Usuario\\Desktop\\LPT1");
//        file.createNewFile();
//        System.out.println(file.getCanonicalPath());
//        File file = new File("path/A/Parent");
//        FileNode fileNode = new FileNode(file);
//        fileNode.addNode(new File("Child"));
//        File parent = new File("path/A/Parent");
//        File child = new File("path/A/Parent/Child");
//        System.out.println(FileUtils.getFilePathWithoutFileName(child));
//        FileNode node = null;
//        try{
//            node = new FileNode(null);
//        }
//        catch (Exception e){
//        }
//
//        System.out.println(node == null);

//
//        File file = new File("Hola/hola");
//        System.out.println("Es dir?: " + file.isDirectory());
//        System.out.println(file.getAbsolutePath());
//        Node<String> node = new Node<>("Hola");
//        String s = null;
//        node.addNode(s);
    }
}
