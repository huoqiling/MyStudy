package com.example.zyfx_.mystudy.view.entitys;

import java.io.Serializable;

public class OperationObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 20150813113950L;
	
	String taskID;
	int count;
	int totalCount;
	boolean isChange;
	boolean isSingle;

	String recDate;
	String tradeItemId;

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getTradeItemId() {
		return tradeItemId;
	}

	public void setTradeItemId(String tradeItemId) {
		this.tradeItemId = tradeItemId;
	}

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
