package vn.devpro.fashionalStoreManagement.sale;

import vn.devpro.fashionalStoreManagement.update.product.Product;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;

public class Product_in_order {
	private int productId;
	private int quantity; //so luong khach mua
	
	public void display() {
		//Lay 1 sp trong danh sach sp
		Product product = new Product();
		product = ProductManagement.getProductById(this.productId);
		System.out.printf("%-20s %,8d %,15.2f%n", product.getName(), this.quantity, total());
	}
	public double total() {
		//Lay 1 sp trong danh sach sp
		Product product = ProductManagement.getProductById(this.productId);
		return this.quantity * product.getPrice();
	}
	
	public Product_in_order() {
		super();
	}
	public Product_in_order(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
