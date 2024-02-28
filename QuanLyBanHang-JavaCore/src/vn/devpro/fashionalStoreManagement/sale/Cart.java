package vn.devpro.fashionalStoreManagement.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.fashionalStoreManagement.update.customer.Customer;
import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;

public class Cart {

	private int id;
	private int customerId;
	private String code;
	private List<Product_in_order> Products_in_order = new ArrayList<Product_in_order>();

	public void display() {
		String customerName ="";
		Customer customer = CustomerManagement.getCustomerById(this.customerId);
		if(customer != null) {
			customerName=customer.getName();
		}
		System.out.println("\tTên khách hàng: "+ customerName);
		System.out.println("\tDanh sách sản phẩm");
		System.out.printf("%-20s %-8s %15s%n",
				"Tên sản phẩm","Số lượng","Thành Tiền");
		for (Product_in_order product_in_order : Products_in_order) {
			product_in_order.display();
		}
		System.out.printf("\tCộng thành tiền: %,.2f",this.totalCartMoney());
	}

	public double totalCartMoney() {
		double total=0;
		for (Product_in_order cartProduct : Products_in_order) {
			total += cartProduct.total();
		}
		return total;
	}

	//Tim sp co trong gio hang hay ko?
	public int findCartProductById(int productId) {
		for (int i = 0; i < Products_in_order.size(); i++) {
			if(Products_in_order.get(i).getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}

	
	public Cart() {
		super();
	}

	public Cart(int id, int customerId, String code, List<Product_in_order> Products_in_order) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.Products_in_order = Products_in_order;
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

	public List<Product_in_order> getProducts_in_order() {
		return Products_in_order;
	}

	public void setProducts_in_order(List<Product_in_order> Products_in_order) {
		this.Products_in_order = Products_in_order;
	}
	
	
}
