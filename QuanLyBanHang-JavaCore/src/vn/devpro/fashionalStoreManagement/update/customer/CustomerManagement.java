package vn.devpro.fashionalStoreManagement.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CustomerManagement {
	static Scanner sc= new Scanner(System.in);
	
	public static int autoId = 1;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();

	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerManagement.customers = customers;
	}

	public static void init() {
		customers.add( new Customer(autoId++,"KH01" ,"Van B","01234"));
		customers.add( new Customer(autoId++,"KH02" ,"Van A","012342"));
		customers.add( new Customer(autoId++,"KH03" ,"Van C","0134"));
	}
	
	public static void execute() {
		
		do {
			System.out.println("\n---------CAP NHAT THONG TIN KHACH HANG----------");
			System.out.println("Chon chuc nang cap nhat: ");
			System.out.println("\t1. Hien thi danh sach khach hang ");
			System.out.println("\t2. Them moi khach hang  ");
			System.out.println("\t3. Sua thong tin khach hang ");
			System.out.println("\t4. Xoa thong tin khach hang ");
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

	// 1- Hien thi
	private static void display() {
		System.out.println("\n---------DANH SACH KHACH HANG----------");
		System.out.printf("%5s %-5s %-30s %-10s%n","Ma LH","Code","Ten khach Hang","SDT");
		for (Customer customer : customers) {
			customer.display();
		}
		
	}

	// 2- Them khach hang moi vao danh sach
	private static void add() {
		System.out.println("\n---------THEM KHACH HANG MOI VAO DANH SACH----------");
		System.out.println("Nhap code khach hang: ");
		String code=sc.nextLine();
		// ktra code da ton tai trong danh sach
		if (findByCode(code) != -1 ) { // code da ton tai
			System.out.println("\tCode: '" + code + "' da co trong danh sach");
			return;
		}
		System.out.println("Nhap ten khach hang: ");
		String name = sc.nextLine();
		System.out.println("Nhap sdt khach hang: ");
		String mobile = sc.nextLine();
		// Ktra sdt da ton tai
		if (findByMobile(mobile) != -1 ) { // sdt da ton tai
			System.out.println("\tSDT: '" + mobile + "' da co trong danh sach");
			return;
		}
		//ktra tinh hop le
		if(code.isEmpty() || name.isEmpty() || mobile.isEmpty()) {
			System.out.println("Code,ho ten va sdt ko dc de trong");
			return;
		}
		
	//Them khach hang vao dnah sach
		//+ Tao doi tuong khach hang
		Customer customer = new Customer(autoId++,code,name,mobile);
		//+ Add doi tuong vao danh sach
		customers.add(customer);
		System.out.println("Them khach hang moi thanh cong");
	}
	
	
	// 3- Sua thong tin khach hang
	private static void edit() {
		System.out.println("\n---------SUA THONG TIN KHACH HANG--------");
		System.out.println("Nhap id khach hang can sua: ");
		int id= Integer.parseInt(sc.nextLine());
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) { //ko co
			System.out.println("khach hang ko co trong danh sach");
			return;
		}
		//Co ==> sua
		customers.get(index).edit(index);
		
	}
	
	// 4 - Xoa khach hang khoi DS
	private static void remove() {
		System.out.println("\n---------XOA THONG TIN KHACH HANG--------");
		System.out.println("Nhap id khach hang can xoa: ");
		int id= Integer.parseInt(sc.nextLine());
		//Tim xem co trong danh sach ko
		int index= findByID(id);
		if(index == -1) {//ko co
			System.out.println("khach hang ko co trong danh sach");
			return;
		}
		//co --> Xoa khoi DS
		customers.remove(index);
		System.out.println("Xoa khach hang thanh cong");
	}

	// 5- Sap xep danh sach theo ten
	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
		//Ham tim kiem khach hang theo code
			public static int findByCode(String code) {
				for (int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getCode().equalsIgnoreCase(code)) {
						return i;
					}
				}
				return -1;
			}
			
			//ham tim kiem ten khach hang da ton tai hay chua
			public static int findByName(String name) {
				for (int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getName().equalsIgnoreCase(name)) {
						return i;
					}
				}
				return -1;
			}
			
			//ham tim kiem khach hang theo sdt
			public static int findByMobile(String mobile) {
				for (int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getMobile().equalsIgnoreCase(mobile)) {
						return i;
					}
				}
				return -1;
			}

		//Ham tim kiem khach hang theo ID
		public static int findByID(int id) {
			for (int i = 0; i < customers.size(); i++) {
				if(customers.get(i).getId() == id) {
					return i;
				}
			}
			return -1;
		}

		//Lay 1 khach hang theo ID
		public static Customer getCustomerById(int id) {
			for (Customer customer : customers) {
				if(customer.getId()==id) {
					return customer;
				}
			}
			return null;
		}	
}
