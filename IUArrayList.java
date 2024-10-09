import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author Morgan Zabriskie
 *
 * @param <T> type to store
 */

 public class IUArrayList<T> implements IndexedUnsortedList<T> {

     private static final int DEFAULT_CAPACITY = 10;
     private static final int NOT_FOUND = -1;

     private T[] array;
     private int rear;
     private int modCount;

     /** Creates an empty list with default initial capacity */
     public IUArrayList() {
         this(DEFAULT_CAPACITY);
     }

     /**
      * Creates an empty list with the given initial capacity
      * 
      * @param initialCapacity
      */
     @SuppressWarnings("unchecked")
     public IUArrayList(int initialCapacity) {
         array = (T[]) (new Object[initialCapacity]);
         rear = 0;
         modCount = 0;
     }

     /** Double the capacity of array */
     private void expandCapacity() {
         array = Arrays.copyOf(array, array.length * 2);
     }

    @Override
    public void addToFront(T element) { 
        // TODO check for array length and increase if necessary
        for(int i = rear; i > 0; i--) {
            array[i] = array[i - 1];
            modCount++;
        }
        array[0] = element;
        // TODO check if I need to add another modcount here
        rear++;
    }

    @Override
    public void addToRear(T element) {
        // TODO check array size is right and increase if necessary
        array[rear] = element;
        rear++;
        modCount++;
    }

    @Override
    public void add(T element) {
        // TODO check array size is right and increase if necessary
        array[rear] = element;
        rear++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) {
        // TODO Auto-generated method stub
        // TODO check array size and increase if needed
        int elementLocation = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                elementLocation = i;
                break;
            }
        }

        if(elementLocation == -1) {
            throw new NoSuchElementException();
        }
        else {
            for (int i = rear; i >= (elementLocation + 2); i--) {
                array[i] = array[i - 1];
                modCount++;
            }
            array[elementLocation + 1] = element;
            // TODO check if I need to add another modcount here
            rear++;
        }
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }

    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public T first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public T last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
    
 }