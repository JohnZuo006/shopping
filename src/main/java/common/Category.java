package common;

import java.sql.Timestamp;

public class Category {
	private int categoryId;
	private int parentId;
	private String categoryName;
	private String categoryStatus;
	private Timestamp createTime;
	private Timestamp updateTime;
	public Category(int categoryId, int parentId, String categoryName, String categoryStatus, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.categoryId = categoryId;
		this.parentId = parentId;
		this.categoryName = categoryName;
		this.categoryStatus = categoryStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
