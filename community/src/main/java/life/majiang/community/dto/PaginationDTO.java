package life.majiang.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
	private List<QuestionDTO> questions;
	private boolean showPrevious;
	private boolean showFirstPage;
	private boolean showNext;
	private boolean showEndPage;
	
	//當前頁
	private Integer page;
	
	//組成頁次排序，例:1,2,3,4,5
	private List<Integer> pages = new ArrayList<>();
	
	//總頁數
	private Integer totalPage;
	
	
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	public boolean isShowPrevious() {
		return showPrevious;
	}
	public void setShowPrevious(boolean showPrevious) {
		this.showPrevious = showPrevious;
	}
	public boolean isShowFirstPage() {
		return showFirstPage;
	}
	public void setShowFirstPage(boolean showFirstPage) {
		this.showFirstPage = showFirstPage;
	}
	public boolean isShowNext() {
		return showNext;
	}
	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}
	public boolean isShowEndPage() {
		return showEndPage;
	}
	public void setShowEndPage(boolean showEndPage) {
		this.showEndPage = showEndPage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<Integer> getPages() {
		return pages;
	}
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	public void setPagination(Integer totalCount, Integer page, Integer size) {		
		
		if ( totalCount % size ==0 ) {
			//總頁數
			totalPage = totalCount / size;
		} 
		else {
			//總頁數
			totalPage = totalCount / size + 1;			
		}
				
		//當前頁
		this.page = page;
		
		//組成頁次排序
		pages.add(page);//[4]
		for (int i=1;i<=3;i++) {
			if (page-i>0) {
				pages.add(0,page-i);//[1,2,3,4]
			}
			
			if (page+i <= totalPage) {
				pages.add(page+i);//[1,2,3,4,5]
			}
		}
		
		
		
		if ( page ==1 ) {
			//"<"不顯示
			showPrevious = false;
		}
		else {
			//"<"顯示
			showPrevious = true;
		}
		
		if (page == totalPage) {
			showNext = false;
		}
		else {
			showNext =true;
		}
		
		if (pages.contains(1)) {
			showFirstPage = false;
		}
		else {
			showFirstPage = true;
		}
		
		if (pages.contains(totalPage)) {
			showEndPage = false;
		}
		else {
			showEndPage = true;
		}
		
	}
	
	
}
