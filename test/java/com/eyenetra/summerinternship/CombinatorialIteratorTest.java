package com.eyenetra.summerinternship;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CombinatorialIteratorTest {

	private Set<String> set;
	private CombinatorialIterator<String> cI;
	
	@Before
	public void init() {
		set = new HashSet<>();
		set.add("A"); 
		set.add("B");
		set.add("C");
		cI = new CombinatorialIterator<String>(set);
	}
	
	@Test
	public void testNumberOfSubsets() {
		set.clear();
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 1);
		set.add("A"); 
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 2);
		set.add("B");
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 4);
		set.add("C"); 
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 8);
		set.add("D");
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 16);
		set.add("E"); 
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 32);
		set.add("F");
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 64);
		set.add("G"); 
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 128);
		set.add("H");
		assertTrue(new CombinatorialIterator<String>(set).numberOfSubsets() == 256);
	}

	@Test
	public void testGet() {
		/* index=0 => 000 => Set() */
		assertTrue(cI.get(0).isEmpty());
		
		/* index=3 => 011 => Set(B, C) */
		assertTrue(!cI.get(3).contains("A"));
		assertTrue(cI.get(3).contains("B"));
		assertTrue(cI.get(3).contains("C"));
		
		/* index=6 => 110 => Set(A, B) */
		assertTrue(cI.get(6).contains("A"));
		assertTrue(cI.get(6).contains("B"));
		assertTrue(!cI.get(6).contains("C"));		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetIndexOutOfUpperBoundException() {
		new CombinatorialIterator<String>(set).get(12);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetIndexOutOfLowerBoundException() {
		new CombinatorialIterator<String>(set).get(-1);
	}

	@Test
	public void testHasNext() {
		int counter = 0;
		while (cI.hasNext()) {
			cI.next(); counter++;
		}
		assertTrue(counter == 8);

		set.clear();
		cI = new CombinatorialIterator<String>(set);
		/*As we consider empty sets as a subset, hasNext returns true even to an empty set */
		cI.next();
		/*hasNext() must return false if called after calling next() in an empty set*/
		assertTrue(!cI.hasNext());
	}

	@Test(expected=NoSuchElementException.class)
	public void testNextWithException() {
		set.clear();
		cI = new CombinatorialIterator<String>(set);
		cI.next();
		cI.next();
	}
	
	@Test
	public void testNext() {
		Set<String> expected = new HashSet<String>();
		assertTrue(cI.next().equals(expected));
		expected.add("C");
		assertTrue(cI.next().equals(expected));		
	}

}
