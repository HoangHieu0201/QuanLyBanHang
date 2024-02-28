package vn.devpro.fashionalStoreManagement;

import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.revenue.OrderManagement;
import vn.devpro.fashionalStoreManagement.sale.SaleManagement;
import vn.devpro.fashionalStoreManagement.update.updateManagement;
import vn.devpro.fashionalStoreManagement.update.category.CategoryManagement;
import vn.devpro.fashionalStoreManagement.update.customer.Customer;
import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;


public class StoreManagement {
public static void main(String[] args) {
		
		CategoryManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n==================CHƯƠNG TRÌNH QUẢN LÝ BÁN HÀNG THỜI TRANG===================");
			System.out.println("Chọn 1 chức năng quản lý");
			System.out.println("\t1. Cập nhật thông tin hệ thống");
			System.out.println("\t2. Quản lý phiên giao dịch của khách hàng");
			System.out.println("\t3. Quản lý đơn hàng và doanh thu");
			System.out.println("\t0. Thoát khỏi chương trình!");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				updateManagement.execute();;
				break;
			case 2:
				SaleManagement.execute();
				break;
			case 3:	
				OrderManagement.execute();
				break;
			case 0: 
				System.out.println("Đã thoát khỏi chương trình !");
				System.exit(0);
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
}
