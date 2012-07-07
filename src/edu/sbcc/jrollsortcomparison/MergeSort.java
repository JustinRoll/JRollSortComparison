/*
 * Classname: MergeSort
 *
 * Version information: .1
 *
 * Date: 05/13/2011
 *
 * Copyright notice: None!
 *
 * Author: Justin Roll
 */

package edu.sbcc.jrollsortcomparison;

public class MergeSort {

   public static void mergeSort(String data[], int firstIndex, int numValues)
   {
       int numOfValues1; //first half
       int numOfValues2; //second half
       int endIndex = firstIndex + numValues;

       if (numValues > 1)
       {
           numOfValues1 = numValues/2;
           numOfValues2 = numValues -numOfValues1;

           mergeSort(data, firstIndex, numOfValues1); //split into 2
           mergeSort(data, firstIndex + numOfValues1, numOfValues2);
           // now we will have 2 sorted halves

           merge(data, firstIndex, numOfValues1, numOfValues2);
       }
   }

   public static void merge (String[] data, int firstIndex, int numOfValues1, int numOfValues2)
   {
       //System.out.println("Merging " + numOfValues1 + " and " + numOfValues2);
       String[] temp = new String[numOfValues1+numOfValues2];
       int numCopied = 0; //data to temp
       int leftPointer = 0; //copied from first half
       int rightPointer = 0; //copied from 2nd half
       int i;

       while ((leftPointer < numOfValues1) && (rightPointer < numOfValues2))
       {
           if (data[firstIndex + leftPointer].compareTo(data[firstIndex+numOfValues1+rightPointer]) <= 0)
           {
               temp[numCopied] = data[firstIndex + (leftPointer)];
               leftPointer++;
               numCopied++;
           }
           else
           {
               temp[numCopied] = data[firstIndex + numOfValues1 + (rightPointer)];
               rightPointer++;
               numCopied++;
           }
       }
       while (leftPointer < numOfValues1)
       {
           temp[numCopied] = data[firstIndex + (leftPointer)];
           leftPointer++;
           numCopied++;
       }
       while (rightPointer < numOfValues2)
       {
           temp[numCopied] = data[firstIndex + numOfValues1 + (rightPointer)];
           rightPointer++;
           numCopied++;
       }
       for (i = 0; i < numOfValues1+numOfValues2; i++)
       {
           data[firstIndex + i] = temp[i];
       }
   }

}