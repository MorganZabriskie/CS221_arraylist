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
        // check array length and increase if rear is in last spot of array
        if (rear == (array.length - 2)) {
            expandCapacity();
        }
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
        // check array length and increase if rear is in last spot of array
        if (rear == (array.length - 2)) {
            expandCapacity();
        }
        array[rear] = element;
        rear++;
        modCount++;
    }

    @Override
    public void add(T element) {
        // check array length and increase if rear is in last spot of array
        if (rear == (array.length - 2)) {
            expandCapacity();
        }
        array[rear] = element;
        rear++;
        modCount++;
    }

    @Override
    public void addAfter(T element, T target) {
        // check array length and increase if rear is in last spot of array
        if (rear == (array.length - 2)) {
            expandCapacity();
        }

        // find location of target element
        int elementLocation = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                elementLocation = i;
                break;
            }
        }

        // add new element after target element
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
        // check array length and increase if rear is in last spot of array
        if (rear == (array.length - 2)) {
            expandCapacity();
        }

        // check index is possible to set something at
        if ((index < 0) || (index > array.length)) {
            throw new IndexOutOfBoundsException();
        } else {
            // shift elements at and after index to the right and insert new value at given index
            for (int i = rear; i > index; i--) {
                array[i] = array[i - 1];
                modCount++;
            }
            array[index] = element;
            // TODO check if I need to add another modcount here
            rear++;
        }
    }

    @Override
    public T removeFirst() {
        T element = array[0];
        array[0] = null;
        // TODO check if I need to add another modcount here

        for (int i = 0; i < rear; i++) {
            array[i] = array[i + 1];
            modCount++;
        }
        rear--;
        array[rear] = null;

        return element;
    }

    @Override
    public T removeLast() {
        T element = array[rear - 1];
        array[rear - 1] = null;
        modCount++;
        rear--;

        return element;
    }

    @Override
    public T remove(T element) {
        int elementLocation = -1;
        T returnElement = element;

        // find location of target element
        for(int i = 0; i < rear; i++) {
            if (array[i] == element) {
                elementLocation = i;
                returnElement = array[i];
                break;
            }
        }

        // move array elements to the left starting at target index
        if (elementLocation == -1) {
            throw new NoSuchElementException();
        } else {
            for(int i = elementLocation; i < rear; i++) {
                array[i] = array[i + 1];
                modCount++; 
            }
            rear--;
        }

        return returnElement;
    }

    @Override
    public T remove(int index) {
        T element = array[index];

        // check index is within possible range
        if ((index < 0) || (index >= array.length)) {
            throw new IndexOutOfBoundsException();
        } else {
            // remove element at index and shift elements after index to the left
            for(int i = index; i < rear; i++) {
                array[i] = array[i + 1];
                modCount++;
            }
            rear--;
        }

        return element;
    }

    @Override
    public void set(int index, T element) {
        if ((index < 0) || (index >= array.length)) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = element;
            // TODO check if I need to add another modcount here
        }
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= array.length)) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    @Override
    public int indexOf(T element) {
        int index = -1;

        for(int i = 0; i < rear; i++) {
            if (array[i] == element) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public T first() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return array[0];
        }
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return array[rear - 1];
        }
    }

    @Override
    public boolean contains(T target) {
        boolean contains = false;

        for(int i = 0; i < rear; i++) {
            if(array[i] == target) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;

        for(int i = 0; i < rear; i++) {
            if (array[i] != null) {
                isEmpty = false;
                break;
            }
        }

        return isEmpty;
    }

    @Override
    public int size() {
        // TODO fix references to length that should be size()
        int size = 0;

        for(int i = 0; i < rear; i++) {
            if (array[i] != null) {
                size++;
            }
        }

        return size;
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