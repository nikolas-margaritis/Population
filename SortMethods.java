
import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Nikolas Margaritis
 *	@since	December 4, 2022
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0)
					swap(arr, inner, inner + 1);
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	public void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
		for (int i = 0; i < arr.size(); i++) {
			int max = 0;
			for (int j = 1; j < arr.size() - i; j++) {
				if (arr.get(j).compareTo(arr.get(max)) > 0)
					max = j;
			}
			swap(arr, max, arr.size() - i - 1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr) {
		for (int i = 0; i < arr.size() - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
					if (arr.get(j).compareToName(arr.get(j - 1)) < 0)
						swap(arr, j, j - 1);
					else
						j = 0;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<City> arr, int type) {
		sort(arr, 0, arr.size() - 1, type);
	}

	/**
	 * sort method for merge sort to split array and merge
	 * 
	 * @param arr	array needed to be sorted
	 * @param left	lower bounds
	 * @param right	upper bounds
	 * @param type	sorting for String or City type (1 for City, 2 for String)
	 */
	public void sort(List<City> arr, int left, int right, int type) {
		if (left < right) {
			int middle = left + (right - left) / 2;

			sort(arr, left, middle, type);
			sort(arr, middle + 1, right, type);

			merge(arr, left, middle, right, type);
		}
	}

	/**
	 * merge methods for merge sort to reconnect arrays
	 * 
	 * @param arr		array needed to be sorted
	 * @param left		lower bounds
	 * @param middle	middle bounds
	 * @param right		right bounds
	 * @param type		sorting for String or City type (1 for City, 2 for String)
	 */
	public void merge(List<City> arr, int left, int middle, int right, int type) {
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<City> leftArr = new ArrayList<City>();
		ArrayList<City> rightArr = new ArrayList<City>();


		for (int i = 0; i < size1; i++) {
			leftArr.add(arr.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(arr.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) {
			if (type == 1) {
				if (leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex)) <= 0) {
					arr.set(index, leftArr.get(leftIndex));
					leftIndex++;
				}
				else {
					arr.set(index, rightArr.get(rightIndex));
					rightIndex++;
				}
			}
			else if (type == 2) {
				if (leftArr.get(leftIndex).compareToName(rightArr.get(rightIndex)) <= 0) {
					arr.set(index, leftArr.get(leftIndex));
					leftIndex++;
				}
				else {
					arr.set(index, rightArr.get(rightIndex));
					rightIndex++;
				}
			}
			index++;
		}

		while (leftIndex < size1) {
			arr.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) {
			arr.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	}
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	/*public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}*/
/*
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
/*
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		

		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
/*
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
*/
}
