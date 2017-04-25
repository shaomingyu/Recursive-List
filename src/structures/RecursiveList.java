package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveList<T> implements ListInterface<T>{
	private int size = 0;
	private Node<T> head;
	private Node<T> curr;
	private int counter;
	public RecursiveList() {	
	}
	@Override
	public int size() {
        return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Appends {@code elem} to the end of this {@code ListImplementation} and
	 * returns itself for convenience.
	 */
	@Override
	public ListInterface<T> insertLast(T elem) {
		if(elem == null) {
			throw new NullPointerException("elem cannot be type null");
		}
		Node<T> node = new Node<T>(elem, null);
		if (isEmpty()) {
			head = node;
		}
		else {
			curr = head;
			counter = size - 1;
			toIndex();
			curr.setNext(node);
		}
		size++;
		return this;
	}
	
	public ListInterface<T> insertFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException("elem cannot be type null");
		}
		Node<T> node = new Node<T>(elem, null);
		if (isEmpty()) {
			head = node;
		}
		else {
			node.setNext(head);
		}
		head = node;
		size++;
		return this;
	}
	
	public ListInterface<T> insertAt(int index, T elem) {
		if(elem == null) {
			throw new NullPointerException("elem cannot be type null");
		}
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			return insertFirst(elem);
		}
		if(index == size) {
			return insertLast(elem); 
		}
		curr = head;
		counter = index - 1;
		toIndex();
		curr.setNext(new Node<T>(elem, curr.getNext()));
		size++;
		return this;
	}
	
	public boolean remove(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(indexOf(elem) == -1) {
			return false;
		}
		else {
			removeAt(indexOf(elem));
			return true;
		}
	}
	
	public T removeFirst() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		Node<T> temp = new Node<T>(null, null);
		temp = head;
		head = head.getNext();
		temp.setNext(null); 
		size--;
		return temp.getData();
	} 
	
	public T removeLast() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		curr = head;
		if(size == 1) {
			return removeFirst();
		}
		counter = this.size() - 2;
		toIndex();
		T temp = curr.getNext().getData();
		curr.setNext(null);
		size--;
		return temp;
	}
	
	public T removeAt(int i) {
		if(i < 0 || i >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(i == 0) {
			return removeFirst(); 
		}
		if(i == size - 1) {
			return removeLast();
		}
		curr = head;
		counter = i - 1;
		toIndex();
		T temp = curr.getNext().getData();
		curr.setNext(curr.getNext().getNext());
		size--;
		return temp;
	}

	/**
	 * Gets the {@code n}th element from this list.
	 */
	@Override
	public T get(int n) {
		if(n >= size()) {
			throw new IndexOutOfBoundsException("n must be within the list");
		}
		if(n < 0) {
			throw new IndexOutOfBoundsException("n must be greater than zero");
		}
		curr = head;
		counter = n;
		toIndex();
		return curr.getData();
	}
	
	@Override
	public T getFirst() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		return head.getData();
	}
	
	@Override
	public T getLast() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		curr = head;
		counter = size - 1;
		toIndex();
		return curr.getData();
	}
	
	private Node<T> toIndex() {
		if(counter == 0) {
			return curr;
		}
		counter--;
		curr = curr.getNext();
		return toIndex();
	}
	
	private int indexOf(T toFind, Node<T> toCheck, int currentIndex) {
		if(toCheck.getData().equals(toFind)) {
			return currentIndex;
		}
		if(toCheck.getNext() == null) {
			return -1;
		}
		else {
			currentIndex++;
			toCheck = toCheck.getNext();
			return indexOf(toFind, toCheck, currentIndex);
		}
	}
	
	public int indexOf(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(this.isEmpty()) {
			return -1 ;
		}
		curr = head;
		return indexOf(elem, curr, 0);
	}
	
	@Override
	public Iterator<T> iterator() {
        return new ListIterator<T>(head);
	}
}

