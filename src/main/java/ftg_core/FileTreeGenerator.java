package ftg_core;

import tree_core.FileNameNode;
import tree_core.Node;
import utils.FileNameNodeUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileTreeGenerator {
    private FileNameNode tree;
    private String rootDirectory;
    public FileTreeGenerator(String rootDirectory, FileNameNode tree) throws IllegalArgumentException{
        if(tree == null || rootDirectory == null) throw new IllegalArgumentException("Invalid data: Null data.");
        File file = new File(rootDirectory);
        if(!file.exists()) throw new IllegalArgumentException("Root path does not exist.");
        else if (!file.isDirectory()) throw new IllegalArgumentException("Root not a directory.");
        this.tree = tree;
        this.rootDirectory = rootDirectory;
    }
    public void generate() throws IOException {
        Iterator<Node<String>> iterator = getIterator();
        while(iterator.hasNext()){
            FileNameNode node = (FileNameNode) iterator.next();
            createFileOrDirectory(node);
        }
    }
    private Iterator<Node<String>> getIterator(){
        return this.tree.iterator();
    }
    private void createFileOrDirectory(FileNameNode node) throws IOException {
        String path = FileNameNodeUtils.getPathForNode(this.rootDirectory, node);
        File file = new File(path);
        if(node.isDirectory())
            file.mkdir();
        else
            file.createNewFile();
    }
}
