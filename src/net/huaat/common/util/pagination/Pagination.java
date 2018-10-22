package net.huaat.common.util.pagination;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-10-8 下午4:43:53
 * @version V1.0
 */
public class Pagination {
	private int pageNumber = 1;
	private int allCount;
	private int pageSize = 10;
	private int pageCount;

	public int getHqlFirstResult() {
		return (this.pageNumber - 1) * this.pageSize;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getAllCount() {
		return this.allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		if (this.allCount % this.pageSize == 0)
			this.pageCount = (this.allCount / this.pageSize);
		else {
			this.pageCount = (this.allCount / this.pageSize + 1);
		}
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
