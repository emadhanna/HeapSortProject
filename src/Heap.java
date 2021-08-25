//Mark Boady
//2021 Drexel University

//A full Heap Implementation

class Heap
{
	//Data Structure Attributes
	protected int[] data;
	protected int max_size;
	protected int current_size;
	
	//Methods
	//Make a new Heap
	public Heap(int capacity) {
	
		this.data = new int[capacity];
		this.current_size = 0;
		this.max_size = capacity;
	}
	
	//Is the heap empty?
	public boolean empty(){
		
		return this.current_size == 0;
	}
	
	//What is the min?
	public int min(){
		
		if (this.empty()) {
			
			return 0;
		}
		
		return this.data[0];
	}
	
	//Get Index of Parent
	protected int parent(int n){
	
		if (n == 0) {
			return -1;
		}
		
		return (n - 1) / 2;
	}
	
	//Get index of Left Child
	protected int left_child(int n){
		
		return 2 * n + 1;
	}
	
	//Get index of Right Child
	protected int right_child(int n){
		
		return 2 * n + 2;
	}
	
	//Swap i and j in heap
	protected void swap(int i, int j){
		
		int temp = this.data[i];
		this.data[i] = this.data[j];
		this.data[j] = temp;
		
		return;
	}
	
	//Just to help debug
	//You can have this do whatever you want.
	//We suggest you have a way to print the heap
	//So you can test it when you get errors
	public void print_heap(){
		
		System.out.println("A Heap!");
        System.out.printf("Heap Current Size: %d\n", this.current_size);
        System.out.printf("Heap Max Size: %d\n", this.max_size);
        System.out.printf("Contents:\n");
        
        for(int i=0; i < this.current_size; i++){
        
        	System.out.printf("%d ",this.data[i]);
        }
        
        System.out.printf("\n");
	}
	
	//Insert a new value
	public void insert(int x){

		if (this.current_size == this.max_size) {
			
			this.expand();
		}
		
		this.data[this.current_size] = x;
		this.current_size++;
		this.upheap(this.current_size-1);
		return;
	}
	
	//Upheap a value at index i
	protected void upheap(int i){
		
		int parentNode = this.parent(i);
		
		if (parentNode < 0) {
			
			return;
		}
		
		int parentValue = this.data[parentNode];
		
		if (parentValue <= this.data[i]) {
			
			return;
		}
		
		this.swap(i, parentNode);
		this.upheap(parentNode);
		
		return;
	}
	
	//Delete the min
	public void deletemin(){
		
		if (this.empty()) {
			
			return;
		}
		
		this.swap(0, this.current_size -1);
		this.current_size = this.current_size -1;
		this.downheap(0);
	}
	
	//Downheap a value at index i
	protected void downheap(int i){
		
		int leftIndex = this.left_child(i);
		int rightIndex = this.right_child(i);
		int minIndex;
		
		if (leftIndex >= this.current_size) {
			
			return;
		}
		
		minIndex = this.pickSmaller(leftIndex, rightIndex);
		
		if (this.data[i] > this.data[minIndex]) {
			
			this.swap(i, minIndex);
			this.downheap(minIndex);
		}
		
		return;
	}
	
	//Expand the size of the array to fit more elements
	protected void expand() {
		
		int newSize = 2 * this.current_size;
		int[] newData = new int[newSize];
		
		for (int counter = 0; counter < this.current_size; counter++) {

			newData[counter] = this.data[counter];
		}
		
		this.data = newData;
		this.max_size = newSize;
		return;
	}
	
	//Given two indexes, pick the smaller of the two
	protected int pickSmaller(int leftIndex, int rightIndex) {
		
		int minIndex;
		
		if (rightIndex >= this.current_size) {
			
			minIndex = leftIndex;
		}else {
			if (this.data[leftIndex] < this.data[rightIndex]) {
				
				minIndex = leftIndex;
			}else {
				
				minIndex = rightIndex;
			}
		}
		
		return minIndex;
	}
}