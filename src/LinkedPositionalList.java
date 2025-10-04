import java.util.Iterator;

public class LinkedPositionalList<E> implements Iterable<E> {
    // --- Nested Node Class (implements Position) ---
    static class Node<E> implements Position<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e) {
            this.element = e;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E e) {
            this.element = e;
        }

        // @Override
        // public String toString() {
        //     if (element == null) return "";
        //     return element.toString();
        // }

        public Node<E> after() {
            return next;
        }

        public Node<E> before() {
            return prev; 
        }
    }
    
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        // ... constructor to create sentinel nodes ...
        this.header = new Node<E>(null);
        this.trailer = new Node<E>(null);
        header.prev = null;
        header.next = trailer;
        trailer.prev = header;
        trailer.next = null;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }       

    public Node<E> first() {
        if (isEmpty()) return null;
        return header.after();
    }

    public Node<E> last() {
        if (isEmpty()) return null;
        return trailer.before();
    }

    public Node<E> before(E e) {
        Node<E> i = header.after();
        while (i != null) {
            if (i.getElement().equals(e)) {
                return i.before();
            }
            i = i.after();
        }
        return null;
    }



    public Node<E> after(E e) {
        Node<E> i = header.after();
        while (i != null) {
            if (i.getElement().equals(e)) {
                return i.after();
            }
            i = i.after();
        }
        return null;
    }

    public Node<E> set(E e, E newE) {
        Node<E> i = header.after();
        while (i.after() != trailer) {
            if (i.getElement().equals(e)) {
                i.setElement(newE);
                return i;
            }
            i = i.after();
        }
        return null;
    }

    public Node<E> remove(E e) {
        Node<E> i = header.after();
        while (i.after() != trailer) {
            if (i.getElement().equals(e)) {
                Node<E> before = i.before();
                Node<E> after = i.after();
                before.next = after;
                after.prev = before;
                size--;
                return i;
            }
            i = i.after();
        }
        return null;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> current = header.next;
        header.next = newNode;
        newNode.prev = header;
        newNode.next = current;
        current.prev = newNode;
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> current = trailer.prev;
        trailer.prev = newNode;
        newNode.next = trailer;
        newNode.prev = current;
        current.next = newNode;
        size++;
    }

    public Node<E> addAfter(E p, E e) {
        Node<E> i = header.after();
        while (i.after() != trailer) {
            if (i.getElement().equals(p)) {
                Node<E> newNode = new Node<>(e);
                Node<E> current = i.after();
                i.next = newNode;
                newNode.prev = i;
                current.prev = newNode;
                newNode.next = current;
                size++;
                return current;
            }
            i = i.after();
        }
        return null;
    }

    public Node<E> addBefore(E p, E e) {
        Node<E> i = header.after();
        while (i.after() != trailer) {
            if (i.getElement().equals(p)) {
                Node<E> newNode = new Node<>(e);
                Node<E> current = i.before();
                i.prev = newNode;
                newNode.next = i;
                current.next = newNode;
                newNode.prev = current;
                size++;
                return current;
            }
            i = i.after();
        }
        return null;
    }


    public void display() {
        Node<E> current = header.next;
        while (current != trailer) {
            System.out.print(current.getElement() + " -> ");
            current = current.next;
        }
        System.out.println();
    }

    
    // ... Implement all the Positional List methods ...

    // --- Nested Iterator Class ---
    class ElementIterator<E> implements Iterator<E> {
        LinkedPositionalList.Node<E> cursor =  (LinkedPositionalList.Node<E>) header; // Start at the first element
        
        public boolean hasNext() {
            return cursor != null;
        }
        
        public E next() {
            if (cursor.after() == trailer) {
                cursor = null;
                return null;
            }
            cursor = cursor.after();
            E element = cursor.getElement();
            return element;
            
            // Store the element at the current cursor
            // Advance the cursor to the next position using after()
            // Return the stored element
        }

    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator<E>();
    }
}