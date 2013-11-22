/**
 * a min Heap
 * not tested
 * insert and remove min
 */
class myHeap {
	private int[] heapArray;
	private int size;
	
	public myHeap() {
		size = 0;
		heapArray = new int[1024];
	}
	
	public void insert(int x) {
		if(size >= heapArray.length - 1) {
			newArray = new int[heapArray.length * 2];
			System.arraycopy(heapArray, 0, newArray, 0, size);
			heapArray = newArray;
		}
		heapArray[size] = x;
		int tmp = size - 1;
		while(tmp > 0 || heapArray[tmp] < heapArray[tmp / 2]) {
			swap(tmp + 1, tmp / 2);
			tmp = (tmp / 2) - 1;
		}
		size++;
	}
	
	public int removeMin() {
		int toRet = heapArray[0];
		heapArray[0] = heapArray[size - 1];
		size--;
		while((tmp + 1) * 2 <= size)) {
			int nxt = tmp * 2 + 1;
			if(heapArray[nxt] < heapArray[nxt + 1]) {
				if(heapArray[tmp] > heapArray[nxt])
					swap(tmp, nxt);
				else
					break;
			}
			else {
				if(heapArray[tmp] > heapArray[++nxt])
					swap(tmp, nxt);
				else
					break;
			}
		}
		return toRet;
	}
	
	public int min() {
		return heapArray[0];
	}
	
	private void swap(int index1, int index2) {
		int temp = heapArray[index1];
		heapArray[index1] = heapArray[index2];
		heapArray[index2] = temp;
	}
}
