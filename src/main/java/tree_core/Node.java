package tree_core;
;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Node<T> implements Iterable<Node<T>> {
    protected int level;
    private T data;
    protected Node<T> parent;
    protected List<Node<T>> children;
    public Node(){
        this.children = new LinkedList<>();
        this.level = 0;
        this.parent = null;
    }
    public Node(T data){
        this();
        this.data = data;
        this.parent = null;
    }
    /*Queda codigo duplicado con el otro addNode porque sino luego por temas de polimorfismo
    * cuando addNode(T data) llame a addNode(Node<T> node) ira a buscar la implementacion de la subclase*/
    public boolean addNode(T data){
        Node<T> newNode = new Node<>(data);
        if(newNode != null){
            newNode.setParent(this);
            newNode.setLevel(this.level + 1);
            return this.children.add(newNode);
        }
        else return false;
    }
    public boolean addNode(Node<T> node) {
        if(node != null){
            node.setParent(this);
            node.setLevel(this.level + 1);
            return this.children.add(node);
        }
        else return false;
    }
    public void removeNode(Node<T> node){
        this.children.remove(node);
        node.setParent(null);
    }
    public boolean isRoot(){
        return this.parent == null;
    }
    public boolean isLeaf(){
        return this.children.size() == 0;
    }
    public int getLevel(){
        return this.level;
    }
    private void setParent(Node<T> parentNode){
        this.parent = parentNode;
    }
    private void setLevel(int level){
        this.level = level;
    }
    public T getData(){
        return this.data;
    }
    public Node<T> getParent(){return this.parent;}
    protected void setData(T data){
        this.data = data;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        String separator = "   ";
        builder.append(this.data != null ? this.data.toString() : "[null data]");
        builder.append("\n");
        for(Node<T> node : this.children){
            builder.append(separator.repeat(this.level + 1));
            builder.append(node.toString());
        }
        return builder.toString();
    }
    @Override
    public Iterator<Node<T>> iterator() {
        Iterator<Node<T>> iterator = new NodeIterator<>(this);
        return iterator;
    }
}
