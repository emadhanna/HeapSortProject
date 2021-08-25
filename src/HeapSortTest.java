//Mark Boady - CS502
//Heap Sort Experiment

//This function tests heapsort to see how fast it is in practice.

//Libraries
import java.util.Random;

class HeapSortTest
{
	//Random Number Generator
	//A computer cannot get truely random numbers.
	//It can generate a sequence that looks random.
	//To start the sequence at an unpredicatable point, use current time
	public static final Random RAND = new Random(System.currentTimeMillis());

	//The main function runs all the tests.
	public static void main(String[] argv)
	{
		//How many random arrays to test at each size.
		int arrays_per_size=10;
		int current_size=8;
		int max_size=131072;
	
		//Loop over different sizes
		while(current_size <= max_size)
		{
			//Run Tests and print results
			test_size(current_size,arrays_per_size);
			//Set up for next test
			current_size=current_size*2;
		}
		//End the Main Function
		return;
	}

	//--------------Function Definitions Below This Line---------------
	//This function checks to see if an array is sorted.
	//It returns 1 if the array is sorted.
	//It returns 0 if the array is not sorted.
	public static boolean is_sorted(int[] Array, int size)
	{
		int i=1;
		while(i < size)
		{
			if(Array[i] < Array[i-1])
				return false;//Not Sorted!
			i=i+1;
		}
		return true;
	}
	//This function gives you a random array.
	//It creates a new array of size given and returns the pointer to it.
	//The array will be have random numbers in it.
	public static int[] random_array(int size)
	{
		//We need to ask the computer for memory
		int[] Array = new int[size];
		int counter = 0;
		while(counter < size)
		{
			Array[counter] = RAND.nextInt(10*size);
			counter=counter+1;
		}
		return Array;
	}
	//This function runs multiple tests for a specific size.
	//It prints a summary of the results.
	public static void test_size(int size, int num_of_tests)
	{
		int tests_run=0;
		int tests_passed=0;
		//Clock Items for timing
		long start;
		long end; 
		long time_used;
		long total_time=0;//For computing average
		while(tests_run < num_of_tests)
		{
			//Generate a random array
			int[] my_random_array = random_array(size);
			//RUN THE TEST
			start=System.currentTimeMillis();
			//Use a Heap Sort
			heapsort(my_random_array, size);
			end = System.currentTimeMillis();
			//END OF TEST RUN
			//How long did it take?
			time_used=(end - start);//Convert to seconds
			total_time=total_time+time_used;
			//Did it actually sort?
			if(is_sorted(my_random_array,size))
			{
				tests_passed=tests_passed+1;
			}
			//Count that we ran a tests
			tests_run=tests_run+1;
			//Delete From Memory
			my_random_array=null;//Java will delete now
			//Print out Results
		}
		System.out.printf("Test size: %d\n",size);
		System.out.printf("Tests Ran: %d\n",tests_run);
		System.out.printf("Tests Passed: %d\n",tests_passed);
		System.out.printf("Average Time to Sort: %f milliseconds.\n",
			( (double) total_time)/( (double) tests_run));
	}
	
	//HeapSort Sorts array A with size given using a heap
	public static void heapsort(int[] Array, int size){
		
		Heap heapStructure = new Heap(size);
		for (int counter = 0; counter < size; counter++) {
			
			heapStructure.insert(Array[counter]);
		}
		
		for (int counter = 0; counter < size; counter++) {
			
			Array[counter] = heapStructure.min();
			heapStructure.deletemin();
		}

		return;
	}
}