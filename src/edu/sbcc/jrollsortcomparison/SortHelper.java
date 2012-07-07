/*
 * Classname: SortHelper
 * 
 * Version information: .1
 *
 * Date: 05/13/2011
 * 
 * Copyright notice: None!
 * 
 * Author: Justin Roll
 * 
 * Description: Helper class that executes the different sorts, populates the temporary arrays, verifies the sorts
 */

package edu.sbcc.jrollsortcomparison;

import org.apache.commons.lang.*;

public class SortHelper {

	public int NUM_CHARS = 5;
	public enum SortType { INSERTION, QUICKSORT, MERGESORT, HEAPSORT}
	//this gives us a clean constant which we will use to determine our sort to use
	
	private void masterSort(String[] data, SortType pSort)
	{
		if (pSort == SortType.INSERTION)
			InsertionSort.insertionSort(data, 0, data.length);
		else if (pSort == SortType.QUICKSORT)
			QuickSort.quickSort(data, 0, data.length);
		else if (pSort == SortType.MERGESORT)
			MergeSort.mergeSort(data, 0, data.length);
		else if (pSort == SortType.HEAPSORT)
			System.out.println("Heap sort not supported!");
		else
			System.out.println("Sort not supported!");
	}
	

	
	public String[] buildStringArray(int length)
	{
		String[] strArray = new String[length];
		
		for (int i = 0; i < length; i++)
		{
			strArray[i] = RandomStringUtils.random(NUM_CHARS, true, false).toUpperCase();
		}
		
		return strArray;
	}
	
	public void printStrArray(String[] strArray)
	{
		for (String str : strArray)
			System.out.println(str);
	}
	
	public static boolean sortVerify(String[] strArray) {
		for (int i = 0; i < strArray.length; i++) {
			if (strArray.length == 1) {
				System.out.println("Array has only one element, so it is sorted. Element is " + strArray[i]);
				return true;
			} else if (i == 0) {
				System.out.println("Skip the compare at the first element since there are none beneath it.");
				continue;
			} else if (strArray[i].compareToIgnoreCase(strArray[i - 1]) < 0) {
				System.out.println("Sort Failed. Element " + strArray[i] + "at index " + i
						+ " should be > than element " + strArray[i - 1] + " at index " + (i - 1));
				return false;
			}
		}
		System.out.println("Made it to the end of the loop. Array is sorted!");
		return true;
	}
	
	public SortResult timedSort(int length, SortType sortType)
	{
		String[] strArray = this.buildStringArray(length);
		
		long start = System.currentTimeMillis();
		masterSort(strArray, sortType);
		long end = System.currentTimeMillis();
		
		if (sortVerify(strArray) == false) {
			System.out.println("Array is not sorted! Check sort code");
			printArray(strArray);
		}
		
		long timeInSeconds = (end - start)/1000;
		SortResult result = new SortResult(SortResult.INSERTION_SORT, timeInSeconds, strArray.length);
		
		strArray = null;
		System.runFinalization();
		System.gc();
		return result;
	}
	
	public void printArray(String[] strArray)
	{
		for (int i = 0; i < strArray.length; i++)
			System.out.println(strArray[i]);
	}
	
}
