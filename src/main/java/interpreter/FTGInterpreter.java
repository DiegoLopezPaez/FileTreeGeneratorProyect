package interpreter;

import tree_core.FileNameNode;

public abstract class FTGInterpreter<T> {
    protected T thingToInterpret;
    public FTGInterpreter(T thingToInterpret){
        this.thingToInterpret = thingToInterpret;
    }
    public abstract FileNameNode interpret();
}
