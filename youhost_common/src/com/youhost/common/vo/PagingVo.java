package com.youhost.common.vo;

import com.youhost.common.DefaultLogableObject;

public class PagingVo extends DefaultLogableObject {
	private int paging_cntPerPage;		// 화면당 게시물수
	private int paging_pagePerPage;		// 링크 페이지 수
	private int paging_pageNo;			// 현재 페이지
	private int paging_totalCnt;		// 전체 게시물수
	private int paging_totalPage;		// 전체 페이지 수
	private int paging_startNo;			// 시작 게시물 번호
	private int paging_endNo;			// 끝 게시물 번호
	private int paging_startPage;		// 시작 링크 페이지
	private int paging_endPage;			// 끝 링크 페이지
	private String isPaging;			// 페이징 여부
	private String paging_orderBy;		// 페이징 정렬
	
	private static final int default_cntPerPage = 20;
	private static final int default_pagePerPage = 10;
	
	public PagingVo(){
		setIsPaging("Y");
		this.calculatePaging();
	}
	
	public int getPaging_cntPerPage() {
		if(paging_cntPerPage<1) return default_cntPerPage;
		return paging_cntPerPage;
	}
	public void setPaging_cntPerPage(int paging_cntPerPage) {
		this.paging_cntPerPage = paging_cntPerPage;
		calculatePaging();
	}
	public int getPaging_pagePerPage() {
		if(paging_pagePerPage<1) return default_pagePerPage;
		return paging_pagePerPage;
	}
	public void setPaging_pagePerPage(int paging_pagePerPage) {
		this.paging_pagePerPage = paging_pagePerPage;
		calculatePaging();
	}
	public int getPaging_pageNo() {
		if(paging_pageNo<1) return 1;
		return paging_pageNo;
	}
	public void setPaging_pageNo(int paging_pageNo) {		
		this.paging_pageNo = paging_pageNo;
		calculatePaging();
	}
	public int getPaging_totalCnt() {
		if(paging_totalCnt<1) return 1;
		return paging_totalCnt;
	}
	public void setPaging_totalCnt(int paging_totalCnt) {		
		this.paging_totalCnt = paging_totalCnt;
		calculatePaging();
	}
	public int getPaging_startNo() {
		return paging_startNo;
	}
	private void setPaging_startNo(int paging_startNo) {
		this.paging_startNo = paging_startNo;
	}
	public int getPaging_endNo() {
		return paging_endNo;
	}
	private void setPaging_endNo(int paging_endNo) {
		this.paging_endNo = paging_endNo;
	}
	public int getPaging_startPage() {
		return paging_startPage;
	}
	private void setPaging_startPage(int paging_startPage) {
		this.paging_startPage = paging_startPage;
	}
	public int getPaging_endPage() {
		return paging_endPage;
	}
	private void setPaging_endPage(int paging_endPage) {
		this.paging_endPage = paging_endPage;
	}
	public String getIsPaging() {
		return isPaging;
	}
	public void setIsPaging(String isPaging) {
		this.isPaging = isPaging;
	}
	public int getPaging_totalPage() {
		return paging_totalPage;
	}
	public void setPaging_totalPage(int paging_totalPage) {
		this.paging_totalPage = paging_totalPage;
	}
	public String getPaging_orderBy() {
		return paging_orderBy;
	}
	public void setPaging_orderBy(String paging_orderBy) {
		this.paging_orderBy = paging_orderBy;
	}
	private void calculatePaging(){		
		// 전체 페이지 수 : 총 게시물수 / 화면당 게시물 수
		setPaging_totalPage((int)Math.ceil((double)getPaging_totalCnt()/(double)getPaging_cntPerPage()));	
		
		// 페이지 링크 - 시작 페이지 : 
		// 현재페이지 / 페이지링크수 == 0 ? ((현재페이지/페이지링크수)-1)페이지링크수 + 1
		// ELSE : 현재페이지/페이지링크수*페이지링크수 + 1
		if(getPaging_pageNo()%getPaging_pagePerPage()==0){
			setPaging_startPage((getPaging_pageNo()/getPaging_pagePerPage() - 1)*getPaging_pagePerPage() + 1);
		}else{
			setPaging_startPage(getPaging_pageNo()/getPaging_pagePerPage()*getPaging_pagePerPage() + 1);
		}
		
		
		// 페이지 링크 - 끝 페이지 : 시작 페이지 + 페이지 링크 수 - 1
		setPaging_endPage(getPaging_startPage()+getPaging_pagePerPage() - 1);

		// 페이지 링크 - 끝 페이지 : 최대 페이지 초과시 최대 페이지
		if(getPaging_endPage()>getPaging_totalPage()){
			setPaging_endPage(getPaging_totalPage());
		}
		
		// 시작 게시물 번호 : ( 현재 페이지 - 1 ) * 화면당 게시물 수 + 1
		setPaging_startNo((getPaging_pageNo()-1)*getPaging_cntPerPage() + 1);
		
		// 끝 게시물 번호 : 현재 페이지 * 화면당 게시물 수
		setPaging_endNo(getPaging_pageNo()*getPaging_cntPerPage());
		
		// 끝 게시물 번호 : 총 게시물 수 초과시 총 게시물 수
		if(getPaging_endNo()>getPaging_totalCnt()){
			setPaging_endNo(getPaging_totalCnt());
		}
		
		/*
		 *  paging_orderBy = null
			paging_cntPerPage = 20
			paging_pagePerPage = 10
			paging_pageNo = 20
			paging_totalCnt = 16542
			paging_totalPage = 828
			paging_startNo = 381
			paging_endNo = 400
			paging_startPage = 21
			paging_endPage = 30
		 */
	}
	public String pagingDebugString(){
		StringBuffer sbDebug = new StringBuffer();
		sbDebug.append("paging_orderBy = "+getPaging_orderBy()+"\n");
		sbDebug.append("paging_cntPerPage = "+getPaging_cntPerPage()+"\n");
		sbDebug.append("paging_pagePerPage = "+getPaging_pagePerPage()+"\n");
		sbDebug.append("paging_pageNo = "+getPaging_pageNo()+"\n");
		sbDebug.append("paging_totalCnt = "+getPaging_totalCnt()+"\n");
		sbDebug.append("paging_totalPage = "+getPaging_totalPage()+"\n");
		sbDebug.append("paging_startNo = "+getPaging_startNo()+"\n");
		sbDebug.append("paging_endNo = "+getPaging_endNo()+"\n");
		sbDebug.append("paging_startPage = "+getPaging_startPage()+"\n");
		sbDebug.append("paging_endPage = "+getPaging_endPage()+"\n");
		return sbDebug.toString();
	}
	public String getPagingForm(){
		StringBuffer sbForm = new StringBuffer();		
		sbForm.append("<input type=\"hidden\" name=\"paging_cntPerPage\" value=\""+getPaging_cntPerPage()+"\">");
		sbForm.append("<input type=\"hidden\" name=\"paging_pagePerPage\" value=\""+getPaging_pagePerPage()+"\">");
		sbForm.append("<input type=\"hidden\" name=\"paging_pageNo\" value=\""+getPaging_pageNo()+"\">");
		return sbForm.toString();
	}
}
