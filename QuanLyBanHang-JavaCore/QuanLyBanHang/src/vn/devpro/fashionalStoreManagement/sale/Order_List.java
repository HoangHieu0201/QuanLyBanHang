package vn.devpro.fashionalStoreManagement.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.revenue.CustomerRevenue;
import vn.devpro.fashionalStoreManagement.revenue.ProductRevenue;
import vn.devpro.fashionalStoreManagement.update.customer.Customer;
import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;
import vn.devpro.fashionalStoreManagement.update.product.Product;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;

public class Order_List {
	
	static Scanner sc = new Scanner(System.in); 
	private static List<Order> orders = new ArrayList<Order>();
    // Tạo một danh sách để lưu thông tin về tổng số tiền thu được theo từng khách hàng đã mua (Danh sách phân loại KH)
    private static List<CustomerRevenue> customerRevenues = new ArrayList<CustomerRevenue>();
    // Tạo một danh sách để lưu thông tin về tổng số tiền thu được theo từng sản phẩm đã bán (Danh sách phân loại SP)
    private static List<ProductRevenue> productRevenues = new ArrayList<ProductRevenue>();
	
	public static List<CustomerRevenue> getCustomerRevenues() {
		return customerRevenues;
	}

	public static void setCustomerRevenues(List<CustomerRevenue> customerRevenues) {
		Order_List.customerRevenues = customerRevenues;
	}

	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		Order_List.orders = orders;
	}
	
	// 1- Hiển thị danh sách các hoá đơn
	public static void display() {
		System.out.println("\n--------THỐNG KÊ DANH SÁCH HOÁ ĐƠN---------");
		for (Order order : orders) {
			order.display();
			System.out.println("----------------------------------------------------------------------------");
		}
	}
	
	// 2- Xoá 1 đơn hàng khỏi danh sách
	public static void deleteOrder() {
		int orderID;
		System.out.println("Nhập id đơn hàng cần xoá: ");
		orderID = Integer.parseInt(sc.nextLine());
		if(findOrderByID(orderID) == -1) {
			System.out.println("Không có đơn hàng này");
			return;
		}
		orders.remove(getOrderByID(orderID));
		System.out.println("\tXoá thành công !");
	}
	
	// 3 - Hiển thị tổng doanh thu có được từ tất cả các hóa đơn.
	public static void TotalRevenue() {
		double s =0;
		for (int i = 0; i < orders.size(); i++) {
			s+=orders.get(i).getTotal();
		}
		System.out.printf("\tTổng doanh thu có được từ tất cả các hóa đơn là: %,.2f" , s);
	}
	
	// 4- Hiển thị tổng số tiền thu được theo khách hàng.
	public static void displayTotalRevenueByCustomer() {
		System.out.println("\n--------TỔNG SỐ TIỀN THU ĐƯỢC THEO KHÁCH HÀNG---------");
		// Lặp qua danh sách đơn hàng để tính tổng số tiền thu được
		for (Order order : orders) {
			int customerId = order.getCustomerId();
			double totalRevenue = order.getTotal();

			// Kiểm tra xem khách hàng đã tồn tại trong danh sách phân loại khách hàng chưa?
			CustomerRevenue existingCustomer = findCustomerRevenue(customerRevenues, customerId);

			if (existingCustomer != null) {
				// Nếu tồn tại, cập nhật tổng số tiền thu được
				existingCustomer.addToTotalRevenue(totalRevenue);
			} else {
				// Nếu chưa tồn tại, thêm mới thông tin khách hàng vào danh sách phân loại
				Customer customer = CustomerManagement.getCustomerById(customerId);
				CustomerRevenue customerRevenue = new CustomerRevenue(customerId, customer.getName(), totalRevenue);
				customerRevenues.add(customerRevenue);
			}
		}

		// Hiển thị kết quả
		System.out.printf("%-2s %-20s %30s%n", "ID", "Khách hàng", "Tổng số tiền thu được");
		for (CustomerRevenue customerRevenue : customerRevenues) {
			System.out.printf("%2d %-20s %,30.2f%n", customerRevenue.getCustomerId(), customerRevenue.getCustomerName(),
					customerRevenue.getTotalRevenue());
		}
	}

	// tìm kiếm thông tin khách hàng trong danh sách phân loại
	private static CustomerRevenue findCustomerRevenue(List<CustomerRevenue> customerRevenues, int customerId) {
		for (CustomerRevenue customerRevenue : customerRevenues) {
			if (customerRevenue.getCustomerId() == customerId) {
				return customerRevenue;
			}
		}
		return null;
	}



	
	// 5- Hiển thị tổng số tiền thu được theo sản phẩm đã bán
	public static void displayTotalRevenueByProductInOrder() {
		System.out.println("\n--------TỔNG SỐ TIỀN THU ĐƯỢC THEO SẢN PHẨM ĐÃ BÁN---------");
		// Lặp qua danh sách đơn hàng để tính tổng số tiền thu được
		for (Order order : orders) {
			for(Product_in_order product_in_order : order.getProducts_in_order()) {
				int productId = product_in_order.getProductId();
				int quantity = product_in_order.getQuantity();
				double totalRevenue = order.getTotal();

				// Kiểm tra xem sản phẩm đã tồn tại trong danh sách phân loại sản phẩm chưa?
				ProductRevenue existingProduct = findProductRevenue(productRevenues, productId);

				// Nếu tồn tại, cập nhật tổng số tiền thu được và số lượng đã bán
				if (existingProduct != null) {
					existingProduct.addTotalQuantitySold(quantity);
					existingProduct.addToTotalRevenue(totalRevenue);
				} else {
					// Nếu chưa tồn tại, thêm mới thông tin sane phẩm vào danh sách phân loại
					Product product = ProductManagement.getProductById(productId);
					ProductRevenue productRevenue = new ProductRevenue(productId ,product.getName(), quantity, totalRevenue);
					productRevenues.add(productRevenue);
				}
			}
		}

		// Hiển thị kết quả
		System.out.printf("%3s %-15s %-25s %-12s%n","STT", "Tên Sản Phẩm", "Tổng số lượng đã bán", "Doanh thu");
		for (int i = 0; i < productRevenues.size(); i++) {
			System.out.printf("%3d %-15s %-25d %-,12.2f%n", i+1 , productRevenues.get(i).getProductName(),
					productRevenues.get(i).getTotalQuantitySold(), productRevenues.get(i).getTotalRevenue());
		}
	}

	// tìm kiếm thông tin sản phẩm trong danh sách phân loại
	private static ProductRevenue findProductRevenue(List<ProductRevenue> productRevenues, int productID) {
		for (ProductRevenue productRevenue : productRevenues) {
			if (productRevenue.getProductID() == productID) {
				return productRevenue;
			}
		}
		return null;
	}
	
	
	
	
	//Tìm 1 đơn hàng có trong danh sách ko?
	public static int findOrderByID(int orderID) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getId() == orderID) {
				return i;
			}
		}
		return -1;
	}
	
	//Lấy 1 đơn hàng thông qua id
	public static Order getOrderByID(int orderID) {
		for(Order order : orders) {
			if(order.getId() == orderID) {
				return order;
			}
		}
		return null;
	}
}

