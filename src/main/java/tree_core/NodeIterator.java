package tree_core;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class NodeIterator<T> implements Iterator<Node<T>> {
    private Node<T> node;
    private Stack<Node<T>> stack;
    NodeIterator(Node<T> node){
        this.node = node;
        this.stack = new Stack<>();
    }
    /*
     * TODO no se si seria necesario verificar si la pila aun tiene datos.
     * podria pasar que mientras se itera alguno de los Nodos o su data se
     * nullee, y podria no funcionar correctamente.
     * */
    @Override
    public boolean hasNext() {
        return node != null;
    }
    @Override
    public Node<T> next() {
        Node<T> ret = this.node;
        this.node = getNext();
        return ret;
    }
    private Node<T> getNext(){
        if(this.node.children.size() != 0){
            this.stack.addAll(this.node.children);
            return this.stack.pop();
        }
        else{
            return this.stack.size() == 0 ? null : this.stack.pop();
        }
    }
}

