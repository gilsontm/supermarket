package Model;

public class Company {

	private String name;
	private String cnpj;
	
	public Company(){
		
	}
	
	public Company(String name, String cnpj){
		this.name = name;
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
