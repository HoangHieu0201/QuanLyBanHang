package vn.devpro.fashionalStoreManagement.update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CategoryManagement {
static Scanner sc= new Scanner(System.in);
	
	public static int autoId = 1;
	public static ArrayList<Category> categories = new ArrayList<Category>();
	
	public static void init() {
		categories.add( new Category(autoId++,"DT01", "Dien tu"));
		categories.add( new Category(autoId++,"TT01", "Thoi trang"));
		categories.add( new Category(autoId++,"GD01", "Gia dung"));
	}
	
	public static void execute() {
		
		do {
			System.out.println("\n---------CẬP NHẬT DANH SÁCH CHỦNG LOẠI----------");
			System.out.println("Chọn chức năng cập nhật: ");
			System.out.println("\t1. Hien thi danh sach loai hang ");
			System.out.println("\t2. Them moi loai hang hoa ");
			System.out.println("\t3. Sua thong tin loai hang ");
			System.out.println("\t4. Xoa thong tin loai hang ");
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

	private static void display() {
		System.out.println("\n---------DANH SACH LOAI HANG----------");
		System.out.printf("%5s %-5s %-30s%n","Ma LH","Code","Ten Loai Hang");
		for (Category category : categories) {
			category.display();
		}
		
	}

	// 1) Them loai hang moi vao danh sach
	private static void add() {
		System.out.println("\n---------THEM LOAI HANG MOI VAO DANH SACH----------");
		System.out.println("Nhap code loai hang: ");
		String code=sc.nextLine();
		//ktra tinh hop le
		if(code.isEmpty()) {
			System.out.println("Code ko dc de trong");
			return;
		}
		//ktra ten da ton tai trong danh sach
		if(findByCode(code)!=-1) {  //Code da ton tai
			System.out.println("\tLoai hang voi ma Code: '"+code+"' da co trong danh sach");
			return;
		}
		
		System.out.println("Nhap ten loai hang: ");
		String name=sc.nextLine();
		//Them loai hang vao danh sach
		//+ Tao doi tuong loai hang
		Category category = new Category(autoId++,code,name);
		//+ Add doi tuong vao danh sach
		categories.add(category);
		System.out.println("Them loai hang moi thanh cong");
	}
	//ham tim kiem code loai hang da ton tai hay chua
	public static int findByCode(String code) {
		for (int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}

	
	// 2) Sua thong tin loai hang
	private static void edit() {
		System.out.println("\n---------SUA THONG TIN LOAI HANG--------");
		System.out.println("Nhap ma loai hang can sua: ");
		int id= Integer.parseInt(sc.nextLine());
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) { //ko co
			System.out.println("Loai hang ko co trong danh sach");
			return;
		}
		
		System.out.println("Nhap code loai hang moi: ");
		String code=sc.nextLine();
		System.out.println("Nhap ten loai hang moi: ");
		String name=sc.nextLine();
		//ktra tinh hop le
		if (code.isEmpty() || name.isEmpty()) {
			System.out.println("Code va ten ko dc de trong");
			return;
		}
		// ktra code da ton tai trong danh sach
		if (findByCode(code) != -1 && findByCode(code) != index ) { // Code da ton tai
			System.out.println("\tCode: '" + code + "' da co trong danh sach");
			return;
		}
		
		//Sua thong tin
		categories.get(index).setCode(code);
		categories.get(index).setName(name);
		System.out.println("Sua ten loai hang thanh cong");
	}
	//Ham tim kiem loai hang theo ID
	public static int findByID(int id) {
		for (int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	// 3- Xoa lai hang khoi DS
	private static void remove() {
		System.out.println("\n---------XOA THONG TIN LOAI HANG--------");
		System.out.println("Nhap ma loai hang can xoa: ");
		int id= Integer.parseInt(sc.nextLine());
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) {//ko co
			System.out.println("Loai hang ko co trong danh sach");
			return;
		}
		//co --> Xoa khoi DS
		categories.remove(index);
		System.out.println("Xoa loai hang thanh cong");
	}

	// 5- Sap xep danh sach theo ten
	private static void sort() {
		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
	
	//Ham tim theo id va tra ve doi tuong 
	public static Category getCategoryByID(int id) {
		for (Category category : categories) {
			if(category.getId()==id) {
				return category;
			}
		}
		return new Category();
	}
}
