package com.youhost.common.vo;

import com.youhost.common.DefaultLogableObject;

public class SearchParamVo extends DefaultLogableObject implements IPagingable {
	private String key;
	private String keyword;
	private PagingVo paging;
	
	public SearchParamVo(){
		paging = new PagingVo();
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public PagingVo getPaging() {
		return paging;
	}
	public void setPaging(PagingVo paging) {
		this.paging = paging;
	}
	@Override
	public boolean getIsPaging() {
		return this.paging.getIsPaging().equalsIgnoreCase("Y");
	}
	@Override
	public int getStartNo() {
		return this.paging.getPaging_startNo();
	}
	@Override
	public int getEndNo() {
		return this.paging.getPaging_endNo();
	}
	
	public int getMysqlStartNo(){
		return this.paging.getPaging_startNo()-1;
	}
}
