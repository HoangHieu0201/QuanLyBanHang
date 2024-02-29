package vn.devpro.fashionalStoreManagement.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;
import vn.devpro.fashionalStoreManagement.update.product.Product;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;

public class Order {
	private int id;
	private int customerId;
	private String code;
	private double total;
	private List<Product_in_order> Products_in_order = new ArrayList<Product_in_order>();
	
	
	public void display() {
		System.out.println("\tMã hoá đơn: " + this.code);
		String customerName = CustomerManagement.getCustomerById(customerId).getName();
		String Mobile = CustomerManagement.getCustomerById(customerId).getMobile();
		System.out.println("\tTên khách hàng: " + customerName);
	    System.out.println("\tSĐT khách hàng: " + Mobile);
	    System.out.println("\tDanh sách hàng hoá");
	    System.out.printf("%-20s %-8s %15s%n", "Tên sản phẩm", "Số lượng", "Thành tiền");
	    
	    for (Product_in_order productInOrder : Products_in_order) {
	        Product product = ProductManagement.getProductById(productInOrder.getProductId());
	        System.out.printf("%-20s %,8d %,15.2f%n", product.getName(), productInOrder.getQuantity(), productInOrder.total());
	    }
	    
	    System.out.printf("\tTổng thành tiền: %,.2f%n", this.total);
	}
	
	

	public Order() {
		super();
	}

	public Order(int id, int customerId, String code, double total, List<Product_in_order> products_in_order) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.total = total;
		Products_in_order = products_in_order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Product_in_order> getProducts_in_order() {
		return Products_in_order;
	}

	public void setProducts_in_order(List<Product_in_order> products_in_order) {
		Products_in_order = products_in_order;
	}


	
	
}
