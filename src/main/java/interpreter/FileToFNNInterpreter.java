package interpreter;

import tree_core.FileNameNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToFNNInterpreter extends FTGInterpreter<File>{
    private final char specialChar = ' ';
    /**Interpreta un archivo de texto a un arbol de nodos pertenecientes a FileNameNode.
     * <br><br>
     * La sintaxis general esperada del archivo debe ser el siguiente:<br>
     * --Cada linea representa un archivo/directorio en el arbol de archivos.<br>
     * --La primera linea representa al directorio padre<br>
     * --La jerarquia se construye con indentacion entre las lineas<br>
     * --La indentacion es representada por un espacio en blanco (whitespace)<br>
     * --La primera linea no tiene que tener indentacion<br><br>
     * Ejemplo de un archivo. Los "-" se pusieron para una mejor vizualizacion, deberian ser espacios en blanco:<br><br>
     * A<br>
     * -B<br>
     * --C<br>
     * --D<br>
     * -E<br>
     * --F<br>
     * ---G<br>
     * -H<br>
     * */
    public FileToFNNInterpreter(File textFile) throws IllegalArgumentException{
        super(textFile);
        validateFile(textFile);
    }

    private void validateFile(File textFile) {
        if(!textFile.exists()){
            throw new IllegalArgumentException("File " + textFile.getName() + " does not exists.");
        }
        if(!textFile.isFile()){
            throw new IllegalArgumentException("File " + textFile.getName() + " is not a file.");
        }
    }

    //TODO talvez se podria realizar un sistema mas rapido para obtener un padre viejo utilizando una lista*/
    @Override
    public FileNameNode interpret() throws IllegalArgumentException{
        FileNameNode fileNameNode = null;
        FileNameNode currentFather = null;
        FileNameNode currentNode = null;
        FileNameNode formerNode;
        String currentLine;
        String currentFileName;
        int currentLevel = 0;
        int formerLevel;
        boolean rootFound = false;
        int currentLineNumber = 1;
        Scanner sc = null;
        try {
            sc = new Scanner(super.thingToInterpret);
        }
        catch (FileNotFoundException fileNotFoundException) {
            //TODO
        }
        if(sc != null){
            while(sc.hasNext()){
                currentLine = sc.nextLine();
                formerLevel = currentLevel;
                currentLevel = getCurrentLevelByCurrentLine(currentLine);
                currentFileName = getCurrentFileName(currentLine, currentLevel);
                formerNode = currentNode;
                validateLevel(currentLevel, rootFound, currentLineNumber);
                if(currentFather == null){
                    validateFirstLevel(currentLevel, currentLineNumber);
                    fileNameNode = new FileNameNode(currentFileName, true);
                    currentNode = fileNameNode;
                    currentFather = currentNode;
                    rootFound = true;
                }
                else if(currentLevel == formerLevel){
                    currentNode = addAndReturnNewCurrentNode(currentFather, currentFileName);
                }
                else if(currentLevel > formerLevel){
                    validateLevelWhileGreaterCurrentLevel(currentLevel, formerLevel, currentLineNumber);
                    currentFather = formerNode;
                    currentFather.setAsDirectory();
                    currentNode = addAndReturnNewCurrentNode(currentFather, currentFileName);
                }
                else {
                    currentFather = getFormerFather(currentFather, currentLevel, formerLevel);
                    currentNode = addAndReturnNewCurrentNode(currentFather, currentFileName);
                }
                currentLineNumber++;
            }
            sc.close();
        }
        return fileNameNode;
    }

    private void validateFirstLevel(int currentLevel, int currentLineNumber) throws IllegalArgumentException{
        if(currentLevel != 0){
            throw new IllegalArgumentException("First line must not have indentation.\nAt line " + currentLineNumber + " in file " + super.thingToInterpret.getName());
        }
    }

    private void validateLevelWhileGreaterCurrentLevel(int currentLevel, int formerLevel, int currentLineNumber) throws IllegalArgumentException{
        if(Math.abs(formerLevel - currentLevel) > 1){
            throw new IllegalArgumentException("Invalid sintaxis at line " + currentLineNumber + " in file " + super.thingToInterpret.getName() + ".\nCheck file's indentation with line " + (currentLevel-1));
        }
    }

    private void validateLevel(int currentLevel, boolean rootFound, int currentLineNumber) throws IllegalArgumentException{
        if(rootFound && currentLevel == 0){
            throw new IllegalArgumentException("Two roots found in file " + super.thingToInterpret.getName() + ".\nAt line " + currentLineNumber);
        }
    }

    private FileNameNode addAndReturnNewCurrentNode(FileNameNode currentFather, String currentFileName) throws IllegalArgumentException{
        FileNameNode newCurrentNode = new FileNameNode(currentFileName);
        currentFather.addNode(newCurrentNode);
        return newCurrentNode;
    }
    private int getCurrentLevelByCurrentLine(String currentLine) {
        int level = 0;
        char[] currentLineArray = currentLine.toCharArray();
        while(isSpecialCharacter(currentLineArray[level])){
            level++;
        }
        return level;
    }
    private boolean isSpecialCharacter(char c){
        return c == this.specialChar;
    }
    private String getCurrentFileName(String currentLine, int currentLevel) {
        return currentLine.substring(currentLevel);
    }
    /**Obtiene el FileNameNode padre anterior desde currentFather tantas veces como el valor
     * absoluto de la diferencia entre currentLevel y formerLevel.
     * Ejemplo: Si la diferencia entre currentLevel y formerLevel da como resultado 1, el valor retornado
     * sera el padre de currentFather; si el resultado es 2, el valor retornado sera el padre del padre
     * de currentFather, y asi sucesivamente.*/
    private FileNameNode getFormerFather(FileNameNode currentFather, int currentLevel, int formerLevel){
        int diff = Math.abs(currentLevel-formerLevel);
        FileNameNode ret = currentFather;
        int i = 0;
        while(ret != null && i++<diff){
            ret = (FileNameNode) ret.getParent();
        }
        return ret;
    }
}
