package interpreter;

import tree_core.FileNameNode;

import java.io.File;

public class InterpreterMain {
    public static void main(String[] args) {
        File file = new File("interpreterexample");
        if(file.exists()){
            FTGInterpreter<File> interpreter = new FileToFNNInterpreter(file);
            FileNameNode tree = interpreter.interpret();
            System.out.println(tree.toString());
        }
    }
}
