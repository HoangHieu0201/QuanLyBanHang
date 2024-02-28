package vn.devpro.fashionalStoreManagement.update.product;

import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.update.category.Category;
import vn.devpro.fashionalStoreManagement.update.category.CategoryManagement;

public class Product {
	private int id;
	private int categoryId;
	private String code;
	private String name;
	private double price;
	
	//phuong thuc hien thi hang hoa
	public void display() {
		Category category = CategoryManagement.getCategoryByID(this.categoryId);
		System.out.printf("%5d %-20s %-10s %-30s %,15.2f%n", this.id,category.getName(),this.code 
				,this.name,this.price);
	}
	
	Scanner sc= new Scanner(System.in);
	//Sua thong tin san pham
	public void edit(int index) {
		
		do {
			System.out.println("\n---------SUA THONG TIN SAN PHAM----------");
			System.out.println("Chon thong tin can sua: ");
			System.out.println("\t1. Sua loai hang(chon lai) ");
			System.out.println("\t2. Sua code san pham ");
			System.out.println("\t3. Sua ten san pham ");
			System.out.println("\t4. Sua don gia san pham ");
			System.out.println("\t0. Quay lai ");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				editCategoryId();
				break;
			case 2:
				editCode(index);
				break;
			case 3:
				editName(index);
				break;
			case 4:
				editPrice();
				break;
	
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
	

	private void editCategoryId() {
		System.out.print("\tNhap (chon) ma loai hang: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		//Ktra categoryId co trong ds loai hang hay ko?
		if(CategoryManagement.findByID(categoryId) == -1) {
			System.out.println("Loai hang ko ton tai");
			return;
		}
		this.setCategoryId(categoryId);
	}

	
	private void editName(int index) {
		System.out.println("Nhap ten moi cua san pham: ");
		String name=sc.nextLine();
		
		//ktra tinh hop le
		if(name.isEmpty()) {
			System.out.println("Ten ko dc de trong");
			return;
		}
		
		//ktra ten da ton tai trong danh sach
		if(ProductManagement.findByName(name)!=-1 && ProductManagement.findByName(name)!= index) {  //Ten da ton tai
			System.out.println("\tTen: '"+name+"' da co trong danh sach");
			return;
		}
		this.setName(name);
	}

	private void editCode(int index) {
		System.out.print("Nhap code moi cua san pham: ");
		String code=sc.nextLine();
		
		//ktra tinh hop le
		if(code.isEmpty()) {
			System.out.println("Code ko dc de trong");
			return;
		}
		
		//ktra code da ton tai trong danh sach
		if(ProductManagement.findByCode(code)!=-1 && ProductManagement.findByCode(code)!= index) {  //Ten da ton tai
			System.out.println("\tCode: '"+code+"' da co trong danh sach");
				return;
		}
		this.setCode(code);
	}

	private void editPrice() {
		System.out.println("Nhap don gia moi: ");
		double price = Double.parseDouble(sc.nextLine());
		if(price<0) {
			System.out.println("Don gia ko dc la so am");
			return;
		}
		this.setPrice(price);
	}

	public Product() {
		super();
	}

	public Product(int id, int categoryId, String code, String name, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
