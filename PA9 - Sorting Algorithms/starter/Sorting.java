// NAME: Kunal Singla
// ID: A15799385
// EMAIL: Kusingla@ucsd.edu

import java.util.Arrays;

/**
 * Class for sorting arrays. Constains insertion sort and merge
 * sort. Inputs for both algorithms is an array.
 */

/**
 * Class that contains sorting algorithms. Includes, insertion sort and
 * merge sort. Both sorts will accept any class type that extends Comparable.
 * @param <T> type for array input
 */
class Sorting<T extends Comparable<T>>
{
    //Constant for merge sort used to divide the array to 1:3 ratio
    private static final int MERGE_SORT_CONSTANT = 4;
    
    /**
     * Method to perform a typical insertion sort on a input array. Sorts from
     * least to greatest.
     * @param array input to sort using insertion sort
     */
    public void insertionSort(T[] array)
    {
        //exceptions for if input or an elemnt in input is null
        if(array == null)
            throw new NullPointerException();

        for(T e : array)
        {
            if(e == null)
                throw new NullPointerException();
        }

        int j = 0;
        for(int i = 1; i < array.length; ++i)
        {
            //prints array after each iteration
            System.out.println(Arrays.toString(array));
            
            //if element is null, throw exception
            if(array[i] == null)
                throw new NullPointerException();

            //index j starts at index i
            j = i;
            //if element at j is less than element before j, swap elements
            while(j > 0 && array[j].compareTo(array[j - 1]) < 0)
            {
                //swapping functionality
                T temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                --j;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * Method used to merge 2 arrays into sorted order.
     * @param array subarray to sort elements.
     * @param leftArray left side of the subarray based on 1:3 ratio
     * @param rightArray right side of the subarray based on 1:3 ratio
     * @param left length of left subarray
     * @param right length of right subarray
     */
    public void merge(T[] array, T[] leftArray, T[] rightArray, int left, 
                                    int right)
    {
        //position indices for merging arrays.
        int mergePos = 0;
        int leftPos = 0;
        int rightPos = 0;

        // Add smallest element from left or right partition to merged numbers
        while(leftPos < left && rightPos < right)
        {
            if(leftArray[leftPos].compareTo(rightArray[rightPos]) <= 0)
            {
                array[mergePos] = leftArray[leftPos];
                ++leftPos;
            }
            else
            {
                array[mergePos] = rightArray[rightPos];
                ++rightPos;
            }
            ++mergePos;
        }

        /* If left partition is not empty, add remaining elements to merged
         * numbers.
         */
        while(leftPos < left)
        {
            array[mergePos] = leftArray[leftPos];
            ++leftPos;
            ++mergePos;
        }

        /* If right partition is not empty, add remaining elements to merged 
         * numbers.
         */
        while(rightPos < right)
        {
            array[mergePos] = rightArray[rightPos];
            ++rightPos;
            ++mergePos;
        }
    }

    /**
     * Method to perform a typical merge sort on a input array. Sorts from
     * least to greatest.
     * @param array input to sort using merge sort
     */
    public void mergeSort(T[] array)
    {
        //exceptions for if input is null or an element of input is null
        if(array == null)
            throw new NullPointerException();

        for(T e : array)
        {
            if(e == null)
                throw new NullPointerException();
        }
        
        int middlePoint = 0;

        //if length of array is one, skip the rest.
        if(array.length <= 1)
            return;
        else if(array.length < MERGE_SORT_CONSTANT)
        {
            //if less than the ratio constant specified set middle point to 1
            middlePoint = 1;
        }
        else
        {
            //set middlePoint (used for dividing array) using ratio constant
            middlePoint = array.length/MERGE_SORT_CONSTANT;
        }

        //divide array into left and right subarrays using middlePoint constant
        T[] left =  Arrays.copyOfRange(array, 0, middlePoint);
        T[] right = Arrays.copyOfRange(array, middlePoint, array.length);

        //perform a recursive mergesort call on each subarray
        mergeSort(left);
        mergeSort(right);

        //merge the arrays into sorted array
        merge(array, left, right, left.length, right.length);

        //print the sorted array
        System.out.println(Arrays.toString(array));
    }

}