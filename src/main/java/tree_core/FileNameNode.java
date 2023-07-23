package tree_core;

import validators.FileAndPathValidator;

public class FileNameNode extends Node<String>{
    private boolean isDirectory;
    public FileNameNode(String fileName){
        super(fileName);
        FileAndPathValidator.validateFileName(fileName);
        this.isDirectory = false;
    }
    public FileNameNode(String fileName, boolean isDirectory) throws IllegalArgumentException{
        super(fileName);
        FileAndPathValidator.validateFileName(fileName);
        this.isDirectory = isDirectory;
    }
    @Override
    public boolean addNode(String fileName) throws IllegalArgumentException{
        FileAndPathValidator.validateFileName(fileName);
        if(this.isDirectory) {
            FileNameNode newFileNameNode = new FileNameNode(fileName);
            return super.addNode(newFileNameNode);
        }
        else
            return false;
    }
    @Override
    public boolean addNode(Node<String> fileNameNode){
        return this.addNode(fileNameNode.getData());
    }
    public boolean addNode(FileNameNode fileNameNode){
        FileAndPathValidator.validateFileName(fileNameNode.getData());
        if(this.isDirectory)
            return super.addNode(fileNameNode);
        else
            return false;
    }
    public boolean isDirectory(){
        return this.isDirectory;
    }
    public void setAsDirectory(){
        this.isDirectory = true;
    }
}
