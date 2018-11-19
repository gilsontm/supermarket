package Model;

public class Employee extends Person {
	
	private Double baseSalary;
	private Integer timeWorked;
	private Integer workingHours;
	
	public Employee() {
		
	}

	public Employee(Integer id, String name, String phone, Double baseSalary,
			Integer timeWorked, Integer workingHours) {
		super(id, name, phone);
		this.baseSalary = baseSalary;
		this.timeWorked = timeWorked;
		this.workingHours = workingHours;
	}
	
	// calcula o salário atual
	// este método é sobrescrito nas subclasses Manager e Clerk
	public Double calculateSalary(){
		return baseSalary;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Integer getTimeWorked() {
		return timeWorked;
	}

	public void setTimeWorked(Integer timeWorked) {
		this.timeWorked = timeWorked;
	}

	public Integer getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(Integer workingHours) {
		this.workingHours = workingHours;
	}
}
