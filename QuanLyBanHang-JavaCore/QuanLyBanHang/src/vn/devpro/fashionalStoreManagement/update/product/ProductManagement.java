package vn.devpro.fashionalStoreManagement.update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.update.category.CategoryManagement;

public class ProductManagement {
	static Scanner sc= new Scanner(System.in);
	
	public static int autoId = 1;
	public static ArrayList<Product> products = new ArrayList<Product>();
	
	public static void init() {
		products.add( new Product(autoId++, 1,"Hang01","Tu lanh samsung",1000000));
		products.add( new Product(autoId++, 2,"Hang02","Quan jean",2000000));
		products.add( new Product(autoId++, 3,"Hang03","May rua bat",3000000));
		products.add( new Product(autoId++, 1,"Hang04","Tu lanh sony",4000000));
		products.add( new Product(autoId++, 2,"Hang05","Ao sweater",5000000));
	}
	
public static void execute() {
		
		do {
			System.out.println("\n---------CAP NHAT THONG TIN SAN PHAM----------");
			System.out.println("Chon chuc nang cap nhat: ");
			System.out.println("\t1. Hien thi danh sach san pham ");
			System.out.println("\t2. Them moi san pham  ");
			System.out.println("\t3. Sua thong tin san pham ");
			System.out.println("\t4. Xoa thong tin san pham ");
			System.out.println("\t5. Sap xep danh sach ");
			System.out.println("\t0. Quay lai ");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				edit();
				break;
			case 4:
				remove();
				break;
			case 5:
				sort();
				break;	
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}

// 1-
	private static void display() {
		System.out.println("\n---------DANH SACH SAN PHAM----------");
		System.out.printf("%5s %-20s %-10s %-30s %15s%n",
				"Id ","Ten Loai Hang" ,"Code SP","Ten SP","Don gia");
		for (Product product : products) {
			product.display();
		}
		
	}

	
	// 2- Them san pham moi vao danh sach
	private static void add() {
		System.out.println("\n---------THEM SAN PHAM MOI VAO DANH SACH----------");
		System.out.print("\tNhap (chon) ma loai hang: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		//Ktra categoryId co trong ds loai hang hay ko?
		if(CategoryManagement.findByID(categoryId) == -1) { //co --> huy
			System.out.println("Loai hang ko ton tai");
			return;
		}
		//ko co ==>them vao
		System.out.println("Nhap code san pham moi: ");
		String code=sc.nextLine();
		//ktra ten da ton tai trong danh sach
		if(findByCode(code)!=-1) {  //code da ton tai
			System.out.println("\tCode: '"+code+"' da co trong danh sach");
			return;
		}
		
		System.out.println("Nhap ten san pham moi: ");
		String name=sc.nextLine();
		//ktra ten da ton tai trong danh sach
		if(findByName(name)!=-1) {  //Ten da ton tai
			System.out.println("\tTen: '"+name+"' da co trong danh sach");
			return;
		}
		
		System.out.println("Nhap don gia san pham moi: ");
		double price = Double.parseDouble(sc.nextLine());
		if(price<0) {
			System.out.println("Don gia ko dc la so am");
			return;
		}
		//ktra tinh hop le
		if(code.isEmpty() || name.isEmpty() ) {
			System.out.println("Code va ten sp ko dc de trong");
			return;
		}

		//tao 1 doi tuong san pham moi
		Product product = new Product(autoId++, categoryId, code, name, price);
		//Them san pham moi vao ds
		products.add(product);
		System.out.println("Them san pham moi thanh cong");
	}
	
// 3 - Sua thong tin san pham
	private static void edit() {
		System.out.println("\n---------SUA THONG TIN SAN PHAM--------");
		System.out.println("Nhap ma san pham can sua: ");
		int id= Integer.parseInt(sc.nextLine());
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) { //ko co
			System.out.println("San pham ko co trong danh sach");
			return;
		}
		products.get(index).edit(index);
		
		System.out.println("Sua thong tin san pham thanh cong");
	}
	
	
	// 4- Xoa SP khoi DS
	private static void remove() {
		System.out.println("\n---------XOA THONG TIN SAN PHAM--------");
		System.out.println("Nhap ma san pham can xoa: ");
		int id= Integer.parseInt(sc.nextLine());
		
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) {//ko co
			System.out.println("san pham ko co trong danh sach");
			return;
		}
		//co --> Xoa khoi DS
		products.remove(index);
		System.out.println("Xoa san pham thanh cong");
	}

	//5- Sap xep danh sach theo ten
	private static void sort() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
	
		
		
	// Ham tim kiem san pham theo ID
	public static int findByID(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// ham tim kiem code san pham da ton tai hay chua
	public static int findByCode(String code) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}

	// ham tim kiem ten san pham da ton tai hay chua
	public static int findByName(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	
	//Lay 1 sp theo Id
	public static Product getProductById(int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}
}
