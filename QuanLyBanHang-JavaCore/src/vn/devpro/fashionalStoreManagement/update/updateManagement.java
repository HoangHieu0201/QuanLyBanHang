package vn.devpro.fashionalStoreManagement.update;

import java.util.Scanner;

import vn.devpro.fashionalStoreManagement.update.category.CategoryManagement;
import vn.devpro.fashionalStoreManagement.update.customer.CustomerManagement;
import vn.devpro.fashionalStoreManagement.update.product.ProductManagement;


public class updateManagement {

	public static void execute() {
		Scanner sc= new Scanner(System.in);
		do {
			System.out.println("\n---------CAP NHAT THONG TIN HE THONG---------");
			System.out.println("Chon chuc nang quan ly: ");
			System.out.println("\t1. Cap nhat thong tin loai hang ");
			System.out.println("\t2. Cap nhat thong tin hang hoa ");
			System.out.println("\t3. Cap nhat thong tin khach hang ");
			System.out.println("\t0. Quay lai ");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice)
			{
				case 1://Cap nhat thong tin loai hang 
					CategoryManagement.execute();
					break;
				case 2://Cap nhat thong tin hang hoa
					ProductManagement.execute();
					break;
				case 3://Cap nhat thong tin khach hang
					CustomerManagement.execute();
					break;
				case 0:
					return;
				default:
					System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
}
