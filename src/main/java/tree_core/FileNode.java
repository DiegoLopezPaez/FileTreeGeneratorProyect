package tree_core;

import validators.FileAndPathValidator;

import java.io.File;
import java.io.IOException;

public class FileNode extends Node<File>{
    public FileNode(File file){
        super(file);
        FileAndPathValidator.validateNullFile(file);
    }
    public boolean addNode(String fileName) throws IllegalArgumentException, IOException {
        if(fileName == null || fileName.equals("")) throw new IllegalArgumentException("File name empty/null");
        String newFileCanonicalPath = super.getData().getCanonicalPath() + File.separator + fileName;
        File newFile = new File(newFileCanonicalPath);
        return addNode(newFile);
    }
    @Override
    public boolean addNode(File file) throws IllegalArgumentException {
        FileAndPathValidator.validateNullFile(file);
        try {
            FileAndPathValidator.validateNewChildPath(this.getData(), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.addNode(file);
    }
    @Override
    public boolean addNode(Node<File> fileNode) throws IllegalArgumentException {
        FileAndPathValidator.validateNullFile(fileNode.getData());
        try {
            FileAndPathValidator.validateNewChildPath(this.getData(), fileNode.getData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.addNode(fileNode);
    }
}

