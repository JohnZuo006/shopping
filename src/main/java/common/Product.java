package common;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private String productSubtitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private int stock;
    private String productStatus;
	private Timestamp createTime;
	private Timestamp updateTime;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSubtitle() {
		return productSubtitle;
	}
	public void setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getSubImages() {
		return subImages;
	}
	public void setSubImages(String subImages) {
		this.subImages = subImages;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
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
	public Product(int productId, int categoryId, String productName, String productSubtitle, String mainImage,
			String subImages, String detail, BigDecimal price, int stock, String productStatus, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productSubtitle = productSubtitle;
		this.mainImage = mainImage;
		this.subImages = subImages;
		this.detail = detail;
		this.price = price;
		this.stock = stock;
		this.productStatus = productStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "product [productId=" + productId + ", categoryId=" + categoryId + ", productName=" + productName
				+ ", productSubtitle=" + productSubtitle + ", mainImage=" + mainImage + ", subImages=" + subImages
				+ ", detail=" + detail + ", price=" + price + ", stock=" + stock + ", productStatus=" + productStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	

}
