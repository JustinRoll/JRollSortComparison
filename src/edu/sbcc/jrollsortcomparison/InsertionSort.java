package edu.sbcc.jrollsortcomparison;

public class InsertionSort {

	public static String[] insertionSort(String[] data, int firstIndex, int numValues)
	{
		String tempString;
		int i;
		int j;
		
		int k;
		
		for (i=1; i<numValues; i++)
		{
			tempString = data[firstIndex+i];
			
			for (j=firstIndex+i; j>firstIndex; j--)
			{
				if (tempString.compareTo(data[j-1]) >= 0)
					break;
			}
			
			// Shift items right to make space
			for (k=firstIndex+i; k>j; k--)
				data[k] = data[k-1];
			
			data[j] = tempString;
		}
		return data;
	}
	
	public static int[] insertionSortInt(int[] data, int firstIndex, int numValues)
	{
		int tempInt;
		int i;
		int j;
		
		int k;
		
		for (i=1; i<numValues; i++)
		{
			tempInt = data[firstIndex+i];
			
			for (j=firstIndex+i; j>firstIndex; j--)
			{
				if (tempInt >= (data[j-1]))
					break;
			}
			
			// Shift items right to make space
			for (k=firstIndex+i; k>j; k--)
				data[k] = data[k-1];
			
			data[j] = tempInt;
		}
		return data;
	}
}
