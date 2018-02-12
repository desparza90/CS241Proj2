/**
 * An Implementation of MaxHeap utilizing an Array
 * @author Daniel Esparza
 * @param <T>
 */
public class MaxHeap<T extends Comparable<? super T>> {
    private T[] heap;
    private int lastIndex;
    private static final int DEFAULT_CAP = 10;
    private static final int MAX_CAP = 100;
    private int swapOne = 0;
    private int swapTwo = 0;
    
    public MaxHeap(){
        this(DEFAULT_CAP);
    }
    public MaxHeap(int cap){
        if(cap < DEFAULT_CAP)
            cap = DEFAULT_CAP;
        else if(cap > MAX_CAP)
            cap = MAX_CAP;
        
        T[] tempHeap = (T[]) new Comparable [cap+1];
        heap = tempHeap;
        lastIndex = 0;
    }
    
    /**
     * Returns number of swaps done when adding a new entry
     * @return number of swaps
     */
    public int getSwapOne(){
        return swapOne;
    }
    
    /**
     * Returns number of swaps done when utilizing reHeap function
     * @return number of swaps
     */
    public int getSwapTwo(){
        int nIndex = lastIndex/2;
        while(nIndex > 0)
            reheap(nIndex--);
        return swapTwo;
    }
    
    /**
     * Adds newEntry to maxHeap array and performs swaps to ensure the array is 
     * a max Heap
     * @param newEntry generic data type to be added to array
     */
    public void add(T newEntry){
        int newIndex = lastIndex +1;
        int parentIndex = newIndex /2;
        while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            swapOne++;
            newIndex = parentIndex;
            parentIndex = newIndex /2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        
    }
    
    /**
     * Simply adds newEntry to maxHeap array
     * @param newEntry generic data type to be added to array
     */
    public void addS(T newEntry){
        int newIndex = lastIndex +1;
        heap[newIndex] = newEntry;
        lastIndex++;
    }
    
    /**
     * Removes the max (or root) from the maxHeap and sorts array to accommodate 
     * change
     * @return entry from the root index of array
     */
    public T removeMax(){
        T root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }
    
    /**
     * Returns root data as it is max
     * @return entry from root index of array
     */
    public T getMax(){
        T root = null;
        if(!isEmpty())
            root = heap[1];
        return root;
    }
    
    /**
     * Returns boolean if the maxHeap is empty
     * @return true if array has no entries false if it does 
     */
    public boolean isEmpty(){
        return lastIndex < 1;
    }
    
    /**
     * returns size of the maxHeap
     * @return index of last entry
     */
    public int  getSize(){
        return lastIndex;
    }
    
    /**
     * Clears all entries from the maxHeap
     */
    public void clear(){
        while (lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }
    
    /**
     * from rIndex this function compares children to verify that the sub-tree 
     * is in fact correct with the max being the parent
     * @param rIndex parent index to check children against
     */
    public void reheap(int rIndex){
        boolean done = false;
        T orphan = heap[rIndex];
        int leftChildIndex = 2*rIndex;
        
        while(!done && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
                largerChildIndex = rightChildIndex;
            if (orphan.compareTo(heap[largerChildIndex]) < 0){
                heap[rIndex] = heap[largerChildIndex];
                swapTwo++;
                rIndex = largerChildIndex;
                leftChildIndex = 2*rIndex;
            }
            else
                done = true;
            
        }
        heap[rIndex] = orphan;
    }
    
    /**
     * return the value in the array from the selected index
     * @param in index in which the function will search for
     * @return generic data that is stored at the selected index
     */
    public T getVal(int in){
        return heap[in];
    }
}