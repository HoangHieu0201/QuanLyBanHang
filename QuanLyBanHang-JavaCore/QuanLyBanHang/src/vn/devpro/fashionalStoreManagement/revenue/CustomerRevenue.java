package vn.devpro.fashionalStoreManagement.revenue;

//Class để lưu thông tin về tổng số tiền thu được theo từng khách hàng
public class CustomerRevenue {
	    private int customerId;
	    private String customerName;
	    private double totalRevenue;

	    public CustomerRevenue(int customerId, String customerName, double totalRevenue) {
	        this.customerId = customerId;
	        this.customerName = customerName;
	        this.totalRevenue = totalRevenue;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public double getTotalRevenue() {
	        return totalRevenue;
	    }

	    public void addToTotalRevenue(double amount) {
	        this.totalRevenue += amount;
	    }
	}
