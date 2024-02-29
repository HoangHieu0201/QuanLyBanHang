package vn.devpro.fashionalStoreManagement.revenue;

import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.sale.Cart;
import vn.devpro.fashionalStoreManagement.sale.Order_List;

public class OrderManagement {

	static Scanner sc= new Scanner(System.in);
	
	public static void execute() {
		
		do {
			System.out.println("\n---------QUẢN LÝ HÀNG VÀ DOANH THU----------");
			System.out.println("Chọn chức năng quản lý: ");
			System.out.println("\t1. Hiển thị danh sách các hóa đơn (đơn hàng).\r\n"
					+ "\t2. Xóa một đơn hàng khỏi danh sách\r\n"
					+ "\t3. Hiển thị tổng doanh thu có được từ tất cả các hóa đơn.\r\n"
					+ "\t4. Hiển thị tổng số tiền thu được theo khách hàng.\r\n"
					+ "\t5. Hiển thị tổng số tiền thu được theo sản phẩm đã bán ");
			System.out.println("\t0. Quay lại ");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				Order_List.display();
				break;
			case 2:
				Order_List.deleteOrder();
				break;
			case 3:
				Order_List.TotalRevenue();
				break;
			case 4:
				Order_List.displayTotalRevenueByCustomer();
				break;	
			case 5:
				Order_List.displayTotalRevenueByProductInOrder();
				break;	
			case 0:
				return;
			default:
				System.out.println("Lựa chọn ko hợp lệ");
			}
		} while (true);
	}
}
