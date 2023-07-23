package utils;

import tree_core.FileNameNode;

import java.io.File;

public abstract class FileNameNodeUtils {
    public static String getPathForNode(String rootDirectory, FileNameNode node) throws IllegalArgumentException{
        if(node == null || rootDirectory == null) throw new IllegalArgumentException("Invalid data. Node or root path are null.");
        StringBuilder path = new StringBuilder(rootDirectory);
        path.append(getPath(node));
        return path.toString();
    }
    public static String getPath(FileNameNode node){
        StringBuilder path = new StringBuilder(node.getData());
        FileNameNode parentNode = (FileNameNode) node.getParent();
        path.insert(0, File.separator);
        if(parentNode == null)
            return path.toString();
        else {
            return path.insert(0, getPath(parentNode)).toString();
        }
    }
}
