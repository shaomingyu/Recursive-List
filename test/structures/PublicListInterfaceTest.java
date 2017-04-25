package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	
	@Test 
	public void testInsertLastAndGetLastAndRemoveLast() {
		list.insertLast("hello");
		assertEquals(1, list.size());
		assertEquals("hello", list.getLast());
		list.insertLast("goodbye");
		assertEquals(2, list.size());
		assertEquals("goodbye", list.getLast());
		list.removeLast();
		assertEquals(1, list.size());
		assertEquals("hello", list.getLast());
	}
	
	@Test
	public void testInsertAtAndGetAtAndRemoveAtAndRemove() {
		list.insertLast("yes");
		list.insertLast("no");
		list.insertLast("idk");
		assertEquals("no", list.get(1));
		list.insertAt(2, "maybe");
		assertEquals("maybe", list.get(2)); 
		list.remove("idk");
		assertEquals(3, list.size());
		assertEquals("no", list.get(1));	
	}
	
	@Test
	public void testIndexOf() {
		list.insertLast("yes");
		list.insertLast("no");
		list.insertLast("maybe");
		list.insertLast("idk");
		assertEquals(0, list.indexOf("yes"));
		assertEquals(1, list.indexOf("no"));
		assertEquals(2, list.indexOf("maybe"));
		assertEquals(3, list.indexOf("idk"));
	}
}
