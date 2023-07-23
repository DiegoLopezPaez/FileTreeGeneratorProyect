package validators;

import utils.FileUtils;

import java.io.File;
import java.io.IOException;

public abstract class FileAndPathValidator {
    public static void validateNewChildPath(File parentFile, File newChildFile) throws IllegalArgumentException, IOException {
        String parentPath = parentFile.getCanonicalPath();
        String childPathWithoutFileName = FileUtils.getFilePathWithoutFileName(newChildFile);
        if(!parentPath.equals(childPathWithoutFileName)){
            throw new IllegalArgumentException("File \"" + newChildFile.getName() + "\" has a different path than parent directory" +
                    "\nparent directory: " + parentPath +
                    "\nchild directory: " + childPathWithoutFileName);
        }
    }
    public static void validateNullFile(File file) throws IllegalArgumentException{
        if(file == null){
            throw new IllegalArgumentException("Null file.");
        }
    }
    public static void validateFileName(String fileName) throws IllegalArgumentException{
        if(!FileUtils.isValidFileName(fileName)) throw new IllegalArgumentException("File name is not valid.\nFile name: <" + fileName + ">");
    }
}
