package Heap;

import MinHeap;
public class Solution {

    public static void main(String[] args){
        //  int N = 4;
        //  int[][] mat= {{16,28,60,64},
        //                {22,41,63,91},
        //                {27,50,87,93},
        //               {36,78,87,94}};
        // int K = 3;

        int N = 4;
        int[][] mat= {{10,20,30,40},
                       {15,25,35,45},
                       {24,29,37,48},
                       {36,78,87,94}};
        int K = 7;


        System.out.println(kthSmallest(mat, N, K));
    }


    public static int kthSmallest(int[][] matrix, int n, int k) {
        MinHeap heap = new MinHeap(n);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                heap.push(matrix[i][j]);
            }
        }

        for(int i = 0; i < k - 1; i++){
            heap.pop();
        }

        heap.Heap = heap.removeExcess();
        if(heap.checkMinHeap(heap.Heap, heap.size)){
            System.out.println("Is MinHeap");
        } else{
            System.out.println("Is not MinHeap");
        }
        
        return heap.peek();
    }
}