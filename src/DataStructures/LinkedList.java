

public class LinkedList{
    private Node head;
    private Node tail;
    
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public LinkedList(Node n){
        this.head = n;
        this.tail = n;
    }
    
    public Node getHead(){return head;}
    public Node getTail(){return head;}
    
    public boolean isEmpty(){return head == null;}
    
    public void pushFront(int key){
        head = new Node(key, head);
    }
    
    public int topFront(){
        if(!isEmpty()) return head.getData();
        throw new RuntimeException("La lista está vacia");
    }
    
    public void popFront(){
        if(!isEmpty()) head = head.next();
        else throw new RuntimeException("La lista está vacia");
    }
    
    public void pushBack(int key){
        Node aux = head;
        while(aux.next() != null){
            aux = aux.next();
        }
        aux.setNext(new Node(key));
        tail = aux.next();
    }
    
    public void printList(){
        Node aux = head;
        while(aux != null){
            System.out.println(aux.getData()+" ");
            aux = aux.next();
        }
    }
    
    public void printListRecursive(Node node){
        System.out.println(node.getData());
        if(node.next()!= null) printListRecursive(node.next());
    }
    
    public int topBack(){
        return tail.getData();
    }
    
    public void popBack(){
        Node aux = head;
        while(aux.next().next() != null){
            aux = aux.next();
        }
        aux.setNext(null);
    }
    
    public boolean findData(int data){
        boolean find = false;
        Node aux = head;
        while(aux.next() != null){
            if(aux.getData() == data) return true;
            aux = aux.next();
        }
        return false;
    }
    
    public void erase(int data){
        Node aux = head;
        while(aux.next().getData() != data && aux.next() != null){
            aux.setNext(aux.next().next()); 
        }
    }
    
    public static void main(String[] args){
        LinkedList lista = new LinkedList(new Node(1));
        lista.pushBack(2);
        lista.pushBack(3);
        lista.pushBack(4);
        lista.pushBack(5);
        lista.pushBack(6);
        lista.pushBack(7);
        lista.pushBack(8);
        lista.pushBack(9);
        lista.pushBack(10);
        lista.printListRecursive(lista.getHead());
    }
}