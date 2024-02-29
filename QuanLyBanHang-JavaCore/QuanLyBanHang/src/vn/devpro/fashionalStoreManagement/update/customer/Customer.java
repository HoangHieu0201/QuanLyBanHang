package vn.devpro.fashionalStoreManagement.update.customer;

import java.util.Scanner;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	
	public void display() {
		System.out.printf("%5d %-5s %-30s %-10s%n", this.id,this.code, this.name,this.mobile);
	}

	
	
	Scanner sc= new Scanner(System.in);
	//Sua thong tin san pham
	public void edit(int index) {
		
		do {
			System.out.println("\n---------SUA THONG TIN KHACH HANG----------");
			System.out.println("Chon thong tin can sua: ");
			System.out.println("\t1. Sua code ");
			System.out.println("\t2. Sua ten  ");
			System.out.println("\t3. Sua sdt ");
			System.out.println("\t0. Quay lai ");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				editCode(index);
				break;
			case 2:
				editName();
				break;
			case 3:
				editMobile(index);
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
	

	private void editCode(int index) {
		System.out.println("Nhap code moi cua khach hang: ");
		String code=sc.nextLine();
		//ktra tinh hop le
		if(code.isEmpty() ) {
			System.out.println("Code,ho ten va sdt ko dc de trong");
			return;
		}
		// ktra code da ton tai trong danh sach
		if (CustomerManagement.findByCode(code) != -1 && CustomerManagement.findByCode(code) != index) { // code da ton tai
			System.out.println("\tCode: '" + code + "' da co trong danh sach");
			return;
		}
		this.setCode(code);
		System.out.println("Sua thong tin khach hang thanh cong!");
	}

	private void editName() {
		System.out.println("Nhap ten moi cua khach hang: ");
		String name=sc.nextLine();
		this.setName(name);
		//ktra tinh hop le
		if( name.isEmpty() ) {
			System.out.println("Code,ho ten va sdt ko dc de trong");
			return;
		}
		System.out.println("Sua thong tin khach hang thanh cong!");
	}

	private void editMobile(int index) {
		System.out.println("Nhap sdt moi cua khach hang: ");
		String mobile=sc.nextLine();
		//ktra tinh hop le
		if( mobile.isEmpty()) {
			System.out.println("Code,ho ten va sdt ko dc de trong");
			return;
		}
		// Ktra sdt da ton tai
		if (CustomerManagement.findByMobile(mobile) != -1 && CustomerManagement.findByMobile(mobile) != index) { // sdt da ton tai
			System.out.println("\tSDT: '" + mobile + "' da co trong danh sach");
			return;
		}
		this.setMobile(mobile);
		System.out.println("Sua thong tin khach hang thanh cong!");
	}

	public Customer() {
		super();
	}
	
	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
