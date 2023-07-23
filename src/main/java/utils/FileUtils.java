package utils;

import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
    public static String getFilePathWithoutFileName(File file) throws IOException {
        String fileName = getFileName(file);
        String filePath = file.getCanonicalPath();
        int fileNameIndex = filePath.lastIndexOf(fileName);
        return filePath.substring(0, fileNameIndex - 1);
    }

    public static String getFileName(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        return getFileName(canonicalPath);

    }
    public static String getFileName(String path){
        int index = path.length();
        boolean separatorFound = false;
        String separator = File.separator;
        while(!separatorFound && index >= 0){
            String currentChar = String.valueOf(path.charAt(--index));
            if(currentChar.equals(separator)){
                separatorFound = true;
            }
        }
        //El +1 es por el tema del separador, si el nombre del archivo
        //es file, index solo me devolvera /file
        return path.substring(index+1);
    }

    public static boolean isValidFileName(String fileName){
        String regexForFileNameCharacters = RegexContainerConstants.REGEX_FOR_FILE_NAME_CHARACTERS;
        String regexForExtensionCondition = RegexContainerConstants.REGEX_FOR_FILE_EXTENSION_CONDITION;
        String regexForExtension = RegexContainerConstants.REGEX_FOR_FILE_EXTENSION;
        String regexForSpecialFileNames = RegexContainerConstants.REGEX_FOR_SPECIAL_FILE_NAMES;
        String regexForLength = RegexContainerConstants.REGEX_FOR_FILE_LENGTH;

        boolean isValid = fileName.matches(regexForFileNameCharacters)
                && fileName.matches(regexForSpecialFileNames)
                && fileName.matches(regexForLength);
        if(fileName.matches(regexForExtensionCondition)){
            isValid = isValid && fileName.matches(regexForExtension);
        }
        return isValid;
    }
}
