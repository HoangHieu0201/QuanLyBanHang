package vn.devpro.fashionalStoreManagement.sale;

import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.update.customer.Customer;
import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;
import vn.devpro.fashionalStoreManagement.update.product.Product;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;


public class SaleManagement {

	//Cần 1 giỏ hàng
	//Các thao tác của khách hàng:
	// + Xem giỏ hàng
	// + Thêm sản phẩm vào giỏ
	// + Sửa số lượng sp trong giỏ
	// + Xoá sp khỏi giỏ
	// + Huỷ giỏ hàng
	// + Thanh toán giỏ hàng
	
	static Scanner sc= new Scanner(System.in);
	
	public static int autoId = 1;
	//public static ArrayList<CartProduct> customers = new ArrayList<CartProduct>();
	
	private static Cart cart = new Cart();
	private static Order order = new Order();
	
	public static void execute() {
		
		do {
			System.out.println("\n---------QUẢN LÝ GIỎ HÀNG----------");
			System.out.println("Chọn chức năng quản lý: ");
			System.out.println("\t1. Hiển thị giỏ hàng ");
			System.out.println("\t2. Thêm sản phẩm vào giỏ hàng ");
			System.out.println("\t3. Thay đổi số lượng sản phẩm trong giỏ hàng");
			System.out.println("\t4. Xóa sản phẩm khỏi giỏ hàng");
			System.out.println("\t5. Hủy giỏ hàng ");
			System.out.println("\t6. Thanh toán giỏ hàng");
			System.out.println("\t0. Quay lại ");
			
			System.out.print("Lựa chọn của bạn: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				System.out.println("\n\t\tGIỎ HÀNG CỦA BẠN");
				if(cart.getProducts_in_order().size() <= 0) {
					System.out.println("\tKhông có sản phẩm nào trong giỏ hàng");
				}
				cart.display();
				break;
			case 2:
				addToCart();
				break;
			case 3:
				changeProductQuantity();
				break;
			case 4:
				deleteProducts_in_order();
				break;	
			case 5:
				cart = new Cart();
				break;
			case 6:
				payment();
				break;	
			case 0:
				return;
			default:
				System.out.println("Lựa chọn không hợp lệ");
			}
		} while (true);
	}
	private static void deleteProducts_in_order() {
		System.out.println("\n-------Xóa SP khỏi giỏ hàng------------");
		System.out.println("Nhập id sản phẩm cần xóa: ");
		int productId = Integer.parseInt(sc.nextLine());
		int index = cart.findCartProductById(productId);
		if(index == -1) {
			System.out.println("Sản phẩm ko có trong giỏ hàng");
			return;
		}
		cart.getProducts_in_order().remove(index);
		System.out.println("Xóa sp thành công");
	}
	private static void addToCart() {
		System.out.println("\n------------THÊM SẢN PHẨM VÀO GIỎ HÀNG-------------");
		System.out.print("\tNhập id sản phẩm cần mua: ");
		int productId = Integer.parseInt(sc.nextLine());
		//Ktra san pham co trong danh sach sp khong?
		int productIndex = ProductManagement.findByID(productId);
		if(productIndex ==-1) {
			System.out.println("\tSản phẩm ko có trong danh sách sản phẩm");
			return;
		}
		//Neu co sp do ==> Nhap so luong
		System.out.print("\tNhập số lượng cần mua: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if(quantity <= 0) {
			System.out.println("\tSố lượng sản phẩm ko hợp lệ");
			return;
		}
		
		//Cap nhat san pham vao gio hang: Co 2 truong hop
		// + TH1: San pham chua co trong gio hang ->Them moi
		// + TH2: San pham da co trong gio hang ->tang so luong
		
		//Tim sp xem co trong gio hang chua?
		int cartProductIndex = cart.findCartProductById(productId);
		//Tinh tong so luong hang du kien mua
		if(cartProductIndex != -1) {  //Sp co trong gio
			//Tong cua so luong moi nhap va so luong da co trong gio
			quantity += cart.getProducts_in_order().get(cartProductIndex).getQuantity();
		}
		
		//Cap nhat gio hang
		if(cartProductIndex == -1) { //TH2: chưa có trong giỏ
			cart.getProducts_in_order().add(new Product_in_order(productId, quantity));
		}
		else { //Có trong giỏ rồi ==> Cập nhật lại số lượng
			cart.getProducts_in_order().get(cartProductIndex).setQuantity(quantity);
		}
		System.out.println("\tThêm sản phẩm mới thành công!");
	}
	
	private static void changeProductQuantity() {
		System.out.println("\n------------THAY ĐỔI SỐ LƯỢNG SẢN PHẨM TRONG GIỎ HÀNG-------------");
		System.out.print("\tNhập id sản phẩm cần thay đổi: ");
		int productId = Integer.parseInt(sc.nextLine());
		//Ktra san pham co trong danh sach sp khong ?
		int cartProductIndex = cart.findCartProductById(productId);
		if(cartProductIndex ==-1) {
			System.out.println("\tSản phẩm ko có trong giỏ hàng");
			return;
		}
		//Co thi nhap so luong
		System.out.println("\tNhập số lượng cần thêm(+)/bớt(-): ");
		int quantity = Integer.parseInt(sc.nextLine());
		//Tinh so luong sau khi them/bot
		quantity += cart.getProducts_in_order().get(cartProductIndex).getQuantity();
		//So luong sau khi them.bot phai ko dc nho hon 1 va ko vuot qua so luong co trong ds
		if(quantity <= 0 ){
			System.out.println("\tSố lượng thêm/bớt ko hợp lệ!");
			return;
		}
		cart.getProducts_in_order().get(cartProductIndex).setQuantity(quantity);
		System.out.println("\tThay đổi số lượng sản phẩm thành công!");
	}
	
	private static void payment() {
		//Cap nhat thong tin khach hang vao hoa don
		//Hien thi hoa don
		//Luu hoa don vao DS hoa don để sau tính tổng tiền các hoá đơn
		//Xoa gio hang
		
		System.out.println("\n---------THANH TOÁN GIỎ HÀNG--------");
		//Cap nhat thong tin khach hang
		System.out.print("\tNhập id khách hàng: ");
		int customerId = Integer.parseInt(sc.nextLine());
		int customerIndex = CustomerManagement.findByID(customerId);
		String customerName="";
		String code="";
		String mobile="";
		if(customerIndex == -1) {  //khach mới
			do {
				System.out.print("\tNhập mã khách hàng: ");
				code=sc.nextLine();
				System.out.print("\tNhập tên khách hàng: ");
				customerName=sc.nextLine();
				System.out.print("\tNhập sđt khách hàng: ");
				mobile=sc.nextLine();
			}while(customerName.isEmpty() || code.isEmpty() || mobile.isEmpty());
			//Them khach hang vao danh sach khach hang
			customerId= CustomerManagement.autoId++;
			Customer customer = new Customer(customerId,code, customerName,mobile);
			CustomerManagement.getCustomers().add(customer);
		}
		else { //Khach da co trong danh sach
			customerName= CustomerManagement.getCustomers().get(customerIndex).getName();
			mobile = CustomerManagement.getCustomers().get(customerIndex).getMobile();
		}
		
		//Cap nhat thong tin hoa don
		order.setId(autoId++);
		order.setCustomerId(customerId);
		order.setCode(order.getId() +"_"+ customerId);
		order.setProducts_in_order(cart.getProducts_in_order());
		order.setTotal(cart.totalCartMoney());
		
		//Luu hoa don vao ds hoa don(Orders)
		Order_List.getOrders().add(order);
		//Hien thi hoa don
		System.out.println("\n\t=======HÓA ĐƠN BÁN HÀNG=======");
		order.display();
		System.out.println("\tCảm ơn bạn đã ủng hộ!");
		//Xoa gio hang
		cart= new Cart();
		order = new Order();
	}
	
}

