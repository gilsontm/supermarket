package Model;

public class Supplier extends Person {
	
	private String productType;
	private Company company;
	
	public Supplier() {
	
	}

	public Supplier(Integer id, String name, String phone, String productType, Company company) {
		super(id, name, phone);
		this.productType = productType;
		this.company = company;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
