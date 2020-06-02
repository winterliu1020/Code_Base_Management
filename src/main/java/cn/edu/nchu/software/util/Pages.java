package cn.edu.nchu.software.util;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Pages {
	/**
	 * pagenum:当前页号
	 * pagesize:页面大小
	 * pageNumAll:总页面数
	 * end:当前序号最后一个
	 * pageBeginToEnd:当前显示序号，最多五个
	 */
	private Integer pageNum;
	private Integer PageSize;
	private Integer pageNumAll;
	private Integer end;
	private ArrayList<Integer> pageBeginToEnd = new ArrayList<>();
	
	public Pages(Integer PageSize) {
		this.PageSize = PageSize;
	}

	public void setPageBeginToEnd() {
		Integer begin = (int) ((Math.ceil(pageNum / 5.0) - 1) * 5 + 1);
		if (pageNumAll < (Math.ceil(pageNum / 5.0) * 5)) {
			end = pageNumAll;
		} else {
			end = (int) (Math.ceil(pageNum / 5.0) * 5);
		}
		for (Integer i = begin; i <= end; i++)
			pageBeginToEnd.add(i);
	}

}
