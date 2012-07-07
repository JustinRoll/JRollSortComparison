package edu.sbcc.jrollsortcomparison;

public class SortResult {
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	long duration;
	int n;
	int sortType;
	
	//Constants for the sort type
	static int INSERTION_SORT = 0; static int QUICK_SORT = 1; static int MERGE_SORT = 2; static int HEAP_SORT = 3;
	
	public SortResult(int sortType, long duration, int n) {
		
		this.sortType = sortType;
		this.duration = duration;
		this.n = n;
		
	}

}
