public class CountInversions {

	static int mergeSort(int arr[]) {
		int[] temp = new int[arr.length];
		return _mergeSort(arr, temp, 0, arr.length - 1);
	}

	static int _mergeSort(int arr[], int temp[], int left, int right) {
		int mid, inv_count = 0;
		if (right > left) {
			mid = (right + left) / 2;

			inv_count = _mergeSort(arr, temp, left, mid);
			inv_count += _mergeSort(arr, temp, mid + 1, right);

			inv_count += merge(arr, temp, left, mid + 1, right);
		}
		return inv_count;
	}

	/*
	 * This funt merges two sorted arrays and returns inversion count in the
	 * arrays.
	 */
	static int merge(int arr[], int result[], int left, int mid, int right) {
		int lIndex, rIndex, resultIndex;
		int inv_count = 0;

		lIndex = left; /* i is index for left subarray */
		rIndex = mid; /* i is index for right subarray */
		resultIndex = left; /* i is index for resultant merged subarray */
		while ((lIndex <= mid - 1) && (rIndex <= right)) {
			if (arr[lIndex] <= arr[rIndex]) {
				result[resultIndex++] = arr[lIndex++];
			} else {
				result[resultIndex++] = arr[rIndex++];

				/* this is tricky -- see above explanation/diagram for merge() */
				inv_count = inv_count + (mid - lIndex);
			}
		}

		/*
		 * Copy the remaining elements of left subarray (if there are any) to
		 * temp
		 */
		while (lIndex <= mid - 1)
			result[resultIndex++] = arr[lIndex++];

		/*
		 * Copy the remaining elements of right subarray (if there are any) to
		 * temp
		 */
		while (rIndex <= right)
			result[resultIndex++] = arr[rIndex++];

		/* Copy back the merged elements to original array */
		for (lIndex = left; lIndex <= right; lIndex++)
			arr[lIndex] = result[lIndex];

		return inv_count;
	}

	/* Driver progra to test above functions */
	public static void main(String[] args) {
		int arr[] = { 1, 20, 6, 4, 5 };
		System.out.print(" Number of inversions are " + mergeSort(arr) + "\n");
	}
}
