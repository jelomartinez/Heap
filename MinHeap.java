package Heap;
import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap{
    public int[] Heap;
    private int index;
    public int size;

    public MinHeap(int size){
        this.size = 0;
        this.index = 0;
        Heap = new int[size];
    }

    public void push(int element){
        if(size >= Heap.length - 1){
            Heap = this.resize();
        }

        size++;
        Heap[size] = element;

        percolateUp();
    }

    public int pop(){
        int element = peek();

        Heap[1] = Heap[size];
        Heap[size] = 0;
        size--;

        percolateDown();
        
        return element;
    }

    protected void percolateUp(){
        int i = this.size;

        while(hasParent(i) && Heap[i] < Heap[parentIndex(i)]){
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    protected void percolateDown(){
        int i = 1;

        while(hasLeftChild(i)){
            int smallerChild = leftIndex(i);

            if(hasRightChild(i) && Heap[leftIndex(i)] > Heap[rightIndex(i)]){
                smallerChild = rightIndex(i);
            }

            if(Heap[i] > Heap[smallerChild]){
                swap(i, smallerChild);
            } else {
                break;
            }

            i = smallerChild;
        }
    }

    public String toString(){
        return Arrays.toString(Heap);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int peek(){
        if(this.isEmpty()){
            throw new IllegalStateException();
        }
        return Heap[1];
    }

    protected boolean hasParent(int i){
        return i > 1;
    }

    protected int leftIndex(int i){
        return i * 2;
    }

    protected int rightIndex(int i){
        return i * 2 + 1;
    }

    protected boolean hasLeftChild(int i){
        return leftIndex(i) <= size;
    }

    protected boolean hasRightChild(int i){
        return rightIndex(i) <= size;
    }

    protected int parent(int i){
        return Heap[parentIndex(i)];
    }

    protected int parentIndex(int i){
        return i / 2;
    }

    protected void swap(int x, int y){
        int temp = Heap[x];
        Heap[x] = Heap[y];
        Heap[y] = temp;
    }

    protected int[] resize(){
        return Arrays.copyOf(Heap, Heap.length * 2);
    }

    protected int[] removeExcess(){
        return Arrays.copyOf(Heap, size);
    }

    public static boolean checkMinHeap(int[] A, int i)
    {
        // if `i` is a leaf node, return true as every leaf node is a heap
        if (2*i + 2 > A.length) {
            return true;
        }
 
        // if `i` is an internal node
 
        // recursively check if the left child is a heap
        boolean left = (A[i] <= A[2*i + 1]) && checkMinHeap(A, 2*i + 1);
 
        // recursively check if the right child is a heap (to avoid the array index out
        // of bounds, first check if the right child exists or not)
        boolean right = (2*i + 2 == A.length) ||
                        (A[i] <= A[2*i + 2] && checkMinHeap(A, 2*i + 2));
 
        // return true if both left and right child are heaps
        return left && right;
    }
}
