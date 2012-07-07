package edu.sbcc.jrollsortcomparison;

import java.util.Random;

public class QuickSort {

	/*
	 * if n> 1 then 1. Partition the array 2. Compute the sizes of the left and right segments (left and right segments
	 * do not include the pivote element 3. Quicksort the left segment 4. Quisksort the right segment
	 */

	/*
	 * Partitioning ----------------------
	 * 
	 * 1. Pivot = first element, tooBigIndex = index of second element, tooSmallIndex = index of last element
	 */

	public static void quickSort(String[] data, int firstIndex, int numValues) {

		int pivotIndex;
		int n1;
		int n2;
		
		if (numValues > 1 && numValues <= 16)
		{
			//System.out.println("numValues is 16 or less,InsertionSorting...");
			InsertionSort.insertionSort(data, firstIndex, numValues);
		}
		
		else if (numValues > 1) {
			// Partition the array, and set the pivot index
			pivotIndex = partition(data, firstIndex, numValues);

			n1 = pivotIndex - firstIndex;
			n2 = numValues - n1 - 1; // now quicksort the two pieces

			quickSort(data, firstIndex, n1);
			quickSort(data, pivotIndex + 1, n2);
		}
	}

	public static int partition(String[] data, int firstIndex, int numValues) {

		String pivot;
		int pivotIndex;

		if (numValues >= 3) {
			Random r = new Random(); // int[] rPivot = {r.nextInt(numValues), r.nextInt(numValues),

			int[] rPivot = { randomInt(firstIndex, numValues + firstIndex - 1, r),
					randomInt(firstIndex, numValues + firstIndex - 1, r),
					randomInt(firstIndex, numValues + firstIndex - 1, r) };
			InsertionSort.insertionSortInt(rPivot, 0, 3);
			pivot = data[rPivot[1]];
			pivotIndex = rPivot[1];
			//System.out.println("Pivot is " + pivot);
			swap(data, firstIndex, pivotIndex); //put the pivot index at the front of the array
		}

		else {
			pivot = data[firstIndex];
			pivotIndex = firstIndex;
		}

		
		
		int endIndex = firstIndex + numValues - 1;

		int tooBigIndex = firstIndex + 1;
		int tooSmallIndex = firstIndex + numValues - 1;
		// the big index and small index help to partition the array
		// you keep moving through them until you find elements that are larger or less than the pivot
		while (tooSmallIndex > tooBigIndex) {
			while (tooSmallIndex > tooBigIndex && tooBigIndex <= endIndex && data[tooBigIndex].compareTo(pivot) <= 0) {
				tooBigIndex++; // System.out.println("Incrementing tooBigIndex");
			}
			//System.out.println("tooBigIndex Stopped at " + tooBigIndex);
			while (tooSmallIndex >= firstIndex && data[tooSmallIndex].compareTo(pivot) > 0
					&& tooSmallIndex >= tooBigIndex) {
				tooSmallIndex--; // System.out.println("Decrementing tooSmallIndex");
			}
			//System.out.println("tooSmallIndex stopped at " + tooSmallIndex);
			if (tooSmallIndex > tooBigIndex) {
				//System.out.println("Moving " + data[tooSmallIndex] + " to the left side, moving " + data[tooBigIndex]
				//		+ " to the right side.");
				swap(data, tooBigIndex, tooSmallIndex);
			}
		}
		
		if (pivot.compareTo(data[tooSmallIndex]) >= 0) {
			//System.out.println("Moving " + data[tooSmallIndex] + " at " + tooSmallIndex + "to position " + firstIndex
			//		+ " which has " + data[firstIndex] + " which will be swapped");
			swap(data, firstIndex, tooSmallIndex);
			return tooSmallIndex;
		} else
			return firstIndex; //needs another partition or the pivot is good

		/*
		 * swap(data, firstIndex, tooSmallIndex); return tooSmallIndex;
		 */
	}

	public static void swap(String data[], int index1, int index2) {
		String tempString = data[index1]; // store the first value in a temp
		data[index1] = data[index2]; // copy the value of the second into the first
		data[index2] = tempString; // copy the value of the temp into the second
	}

	private static int randomInt(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}
}
