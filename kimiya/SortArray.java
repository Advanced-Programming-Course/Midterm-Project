package mid;

public class SortArray {

    /**
     * sort an array with selection sort algorithm
     *
     * @param array  array of integers
     * @param size size of arrays
     * @return sorted array
     */
    public int[] selectionSort(int[] array, int size) {

        int minValue,minIndex;
        int temp = 0;
        for(int i = 0; i < size; i++){
            minValue = array[i];
            minIndex  = i;
            for(int j = i; j < size;j++){
                if(array[j] < minValue){
                    minValue = array[j];
                    minIndex = j;
                }
            }
            if(minValue < array[i]){
                temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    /**
     * sort an array with insertion sort algorithm
     *
     * @param array array of integers
     * @param size size of arrays
     * @return sorted array
     */
    public int[] insertionSort(int[] array, int size) {
        int i,j;
        int key;
        int temp;
        for(i = 1; i < size; i++){
            key = array[i];
            j = i - 1;
            while (j >= 0 && key<array[j]){
                temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
                j--;
            }
        }
        return array;
    }

    /**
     * sort an array with merge sort algorithm
     *
     * @param array  array of integers
     * @param size size of arrays
     * @return sorted array
     */
    public  int[] mergeSort(int [] arr,int size){
        Sort(arr,size);
        return arr;
    }

    public  void Sort(int[] arr, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < size; i++) {
            right[i - mid] = arr[i];
        }
        Sort(left, mid);
        Sort(right, size - mid);

        merge(arr, left, right, mid, size - mid);
    }

    public  void merge(
            int[] arr, int[] leftArr, int[] rightArr, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            }
            else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < left) {
            arr[k++] = leftArr[i++];
        }
        while (j < right) {
            arr[k++] = rightArr[j++];
        }
    }
    /**
     * return position of given value in array which is sorted in ascending order.
     * use binary search algorithm and implement it in iterative form
     *
     * @param array   sorted array
     * @param value value to be found
     * @return position of value in arr. -1 if not exists
     */
    public int binarySearch(int[] array, int value) {
        return binarySearchIter(array,value);
    }

    int binarySearchIter(int arr[], int x) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;


            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    /**
     * return position of given value in array which is sorted in ascending order.
     * use binary search algorithm and implement it in recursive form
     *
     * @param array   sorted array
     * @param value value to be found
     * @return position of value in arr. -1 if not exists
     */
    public int binarySearchRecursive(int[] array, int value) {
        return binarySearchRe(array,0,array.length - 1,value);
    }

    int binarySearchRe(int arr[], int left, int right, int value)
    {
        if (right >= left && left <= arr.length - 1) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == value) {
                return mid;
            }
            if (arr[mid] > value) {
                return binarySearchRe(arr, left, mid - 1, value);
            }
            return binarySearchRe(arr, mid + 1, right, value);
        }
        return -1;
    }
}
