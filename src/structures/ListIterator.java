package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ListIterator<T> implements Iterator<T> {
    private Node<T> head;
    private Node<T> curr;
  
  // Constructors
	public ListIterator(Node<T> head) {
		this.head = head;
	}

	@Override
	public boolean hasNext() {
 		if(head == null) {
			return false;
		}
		else if(curr == null) {
			return true;
		}
		else if(curr.getNext() == null) {
			return false;
		}
		else return true;
	}

	@Override
	public T next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			if(curr == null) {
				curr = head;
			}
			else {
				curr = curr.getNext();
			}
			return curr.getData();
		}
    }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
