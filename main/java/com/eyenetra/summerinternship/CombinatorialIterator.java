package com.eyenetra.summerinternship;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class CombinatorialIterator<T> implements Iterator<Set<T>> {

	private List<T> listOfElements;
	private int numberOfPossibleSubsets;
	private int current;
	
	public CombinatorialIterator(Set<T> set) {
		this.listOfElements = new ArrayList<T>(set);
		this.numberOfPossibleSubsets = numberOfSubsets();
	}
	
	/**
	 * Auxiliary method that calculates the number of possible subsets
	 *  that can be created. The result value considers both empty subset
	 *  and the subset composed by all items of the set. 
	 * @return the number of possible subsets contained in the set
	 */
	public Integer numberOfSubsets() {
		return (int) Math.pow(2, listOfElements.size());
	}

	/**
	 * 
	 * @param index the position in the wrapped list of the requested subset
	 * @return the subset
	 */
	public Set<T> get(int index) {
		if (index < 0 || index > numberOfPossibleSubsets)
			throw new IndexOutOfBoundsException();
		
		String binaryRepresentation = getBinaryRepresentation(index);
		Set<T> result = new HashSet<T>();
		
		for (int i=0; i<binaryRepresentation.length(); i++)
			if (binaryRepresentation.charAt(i) == '1')
				result.add(listOfElements.get(i));
		
		return result;
	}
	
	@Override
	public boolean hasNext() {
		return current < numberOfPossibleSubsets;
	}

	@Override
	public Set<T> next() {
		if (current >= numberOfPossibleSubsets)
			throw new NoSuchElementException();
		Set<T> currentElement = get(current);
		current++;
		return currentElement;
	}
	
	private String getBinaryRepresentation(int index) {
		int repLenght = 1;
		if (listOfElements.size() > 0) 
			repLenght = listOfElements.size();
		String repFormat = "%"+repLenght+"s";
		return String
				.format(repFormat, Integer.toBinaryString(index))
				.replace(' ', '0');
	}
	
}








