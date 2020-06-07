//NAME: KUNAL SINGLA
//ID: A15799385
//EMAIL: KUSINGLA@UCSD.EDU
//I have completed the mid-quarter feedback
/**
 * A custom implementation of a Hash Table Class
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * MyHashTable Class
 * Custom implementation of a hash table utilizing the MyHashTableInterface
 * This class maps keys to values using a hashing algorithm.
 */
public class MyHashTable implements MyHashTableInterface 
{
	//Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;
	final static double MAX_LOAD_FACTOR = 2.0/3.0;
	final static int HASHING_CONSTANT = 0xf0000000;

	LinkedList<String>[] array;	//Array that stores linkedlists
	int nelems;		//Number of element stored in the hash table
	int expand;  	//Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	

	/**
	 * FilePath for the file to write statistics upon every rehash
	 */
	String statsFileName;

	/**
	 * Boolean to decide whether to write stats to file or not after rehashing
	 */
	boolean printStats = false;

	/**
	 * Constructor initializes MyHashTable to given size.
	 * @param size size to set MyHashTable to
	 * @throws IllegalArgumentException size less than zero is not allowed
	 */
	public MyHashTable(int size)
	{
		if(size < 0)
			throw new IllegalArgumentException();
		this.array = new LinkedList[size]; //initialize the array
	}

	/**
	 * Constructor initializes HashTable to given size and allows printing
	 * of MyHashTable statistics.
	 * @param size size to set MyHashTable to
	 * @param fileName file name for statistics
	 */
	public MyHashTable(int size, String fileName)
	{
		if(size < 0)
			throw new IllegalArgumentException();
		if(fileName == null)
			throw new NullPointerException();
		this.array = new LinkedList[size]; //initialize the array
		this.printStats = true; //allows for printing of statistics
		this.statsFileName = fileName; //filename for statistics
	}

	/**
	 * Inserts specified element into MyHashTable
	 * @param value value to insert into MyHashTable
	 * @return returns true if insert was successful
	 * @throws NullPointerException if value is null
	 */
	@Override
	public boolean insert(String value)
	{
		if(value == null)
			throw new NullPointerException();

		//check if table already contains element
		if(this.contains(value))
			return false;
		/** 
		 * if the load factor is above the maximum allowed load factor
		 * then rehash MyHashTable
		 */
		if(this.getFutureLoadFactor() > MAX_LOAD_FACTOR)
			rehash();

		int index = this.hashString(value);

		if(this.array[index] == null)
			this.array[index] = new LinkedList<String>();

		//if reference at index contains elements add and increase collisions
		if(this.array[index].size() > 0)
		{
			this.array[index].add(value);
			this.collision++;
		}
		else
		{
			this.array[index].add(value);
		}

		this.nelems++;
		return true;
	}

	/**
	 * Get current load factor of MyHashTable
	 * @return current load factor of MyHashTable
	 */
	private double getFutureLoadFactor()
	{
		/**
		 * Future load factor equals the number of elements + 1 divided by 
		 * the length of the array
		 */
		return ((double) (this.nelems + 1) / (double)this.array.length);
	}

	/**
	 * Delete element from MyHashTable
	 * @param value value to delete
	 * @return return true if deletion was successful
	 * @throws NullPointerException if value is null
	 */
	@Override
	public boolean delete(String value)
	{
		//null value is not allowed
		if(value == null)
			throw new NullPointerException();
		
		if(!this.contains(value))
			return false;

		int index = this.hashString(value);

		boolean removed = this.array[index].remove(value);

		/**
		 * if element removed was the only one, then set the array at index 
		 * to null.
		 */
		if(this.array[index].size() == 0)
			this.array[index] = null;
		
		//return whether removal was successful
		if(!removed)
			return false;
		else
		{
			this.nelems--;
			return true;
		}

	}

	/**
	 * Checks if specified value exists in MyHashTable
	 * @param value value to check existence of
	 * @return returns true if value exists in MyHashTable, false otherwise
	 * @throws NullPointerException if value is null
	 */
	@Override
	public boolean contains(String value)
	{
		//null value not allowed
		if(value == null)
			throw new NullPointerException();

		int index = this.hashString(value);

		if(this.array[index] == null)
			return false;
		
		return this.array[index].contains(value);
	}

	/**
	 * Prints the elements at each index of MyHashTable
	 */
	@Override
	public void printTable() 
	{
		for(int i = 0; i < this.array.length; ++i)
		{
			//if the index in the array is null, then print and skip it
			if(this.array[i] == null)
			{
				System.out.println(i + ":");
				continue;
			}
			System.out.print(i + ": ");
			for(int j = 0; j < this.array[i].size(); ++j)
			{
				/**
				 * print first element in the list and then each element
				 * afterwards adds a comma and element
				 */
				if(j > 0)
				{
					System.out.print(", " + this.array[i].get(j));
				}
				else
				{
					System.out.print(this.array[i].get(j));
				}
			}
			//new line after every index in the array
			System.out.println();
		}
		return;
	}

	/**
	 * Get number of elements in MyHashTable
	 * @return number of elements in MyHashTable
	 */
	@Override
	public int getSize() 
	{
		return this.nelems;
	}

	/**
	 * Expands MyHashTable to the prime number nearest double the current
	 * number of buckets in MyHashTable
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public void rehash() 
	{
		if(printStats)
			printStatistics();

		int newSize = primeGen();
		
		LinkedList<String>[] tempArray = this.array;
		this.array = new LinkedList[newSize];

		for(int i = 0; i < tempArray.length; ++i)
		{
			if(tempArray[i] == null)
				tempArray[i] = new LinkedList<String>();
			for(int j = 0; j < tempArray[i].size(); ++j)
			{
				String value = tempArray[i].get(j);
				int index = this.hashString(value);
				if(this.array[index] == null)
					this.array[index] = new LinkedList<String>();
				this.array[index].add(value);
			}
		}
		this.expand++;
		this.collision = 0;
	}

	/**
	* Calculate the hash value of a given string using PJW Hash Algorithm
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str)
	{
		int h = 0;
		for(int i = 0; i < str.length(); ++i)
		{
			//top four bits of h are all zero
			h = (h << 4) + (int)str.charAt(i); //shift h 4 bits to the left
			int g = h & HASHING_CONSTANT; //get the top 4 bits of h
			if (g != 0) //if top 4 bits are not zero
			{
				h = h ^ (g >>> 24); //move to low end of h
				h = h ^ g;
			}
			//top four bits of h are agail all zero
		}

		//return value according to PJW Hashing algorithm
		return Math.abs(h) % this.array.length;
	}

	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}

}
