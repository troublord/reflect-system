package com.reflect.demo.instance;

public class PaginationInfo {
	private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalItems;
    
    public PaginationInfo() {
    	
    }

	public PaginationInfo(int currentPage, int pageSize, int totalPages, long totalItems) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.totalItems = totalItems;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	@Override
	public String toString() {
		return "PaginationInfo [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalPages=" + totalPages
				+ ", totalItems=" + totalItems + "]";
	}
    
	
}
