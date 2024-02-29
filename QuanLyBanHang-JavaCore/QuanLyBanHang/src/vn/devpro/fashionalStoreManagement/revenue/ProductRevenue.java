package vn.devpro.fashionalStoreManagement.revenue;

public class ProductRevenue {
	private int productID;
    private String productName;
    private int totalQuantitySold;
    private double totalRevenue;
    
    public void addToTotalRevenue(double amount) {
        this.totalRevenue += amount;
    }
    
    public void addTotalQuantitySold(int amount) {
        this.totalQuantitySold += amount;
    }

	public ProductRevenue() {
		super();
	}

	public ProductRevenue(int productID, String productName, int totalQuantitySold, double totalRevenue) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.totalQuantitySold = totalQuantitySold;
		this.totalRevenue = totalRevenue;
	}

	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotalQuantitySold() {
		return totalQuantitySold;
	}

	public void setTotalQuantitySold(int totalQuantitySold) {
		this.totalQuantitySold = totalQuantitySold;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
    
	
    
}
