package com.nri.busmanagement.model;

import java.util.Date;

public class BookDetails {

	
	private String src;
	private String dst;
	private Date date;
	public BookDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookDetails(String src, String dst, Date date) {
		super();
		this.src = src;
		this.dst = dst;
		this.date = date;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BookDetails [src=" + src + ", dst=" + dst + ", date=" + date + "]";
	}
	
	
}
