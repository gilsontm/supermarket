package Model;

public class Manager extends Employee {

	private Department department;
	
	public Manager() {
		
	}

	public Manager(Integer id, String name, String phone, Double baseSalary,
			Integer timeWorked, Integer workingHours, Department department) {
		super(id, name, phone, baseSalary, timeWorked, workingHours);
		this.department = department;
	}
	
	// calcular salário com base no tempo trabalhado e número de horas;
	@Override
	public Double calculateSalary(){
		if (this.getTimeWorked() > 10) {
			return (1.5 * this.getBaseSalary()) * (1 + this.getWorkingHours() / 20); 
		} else {
			return this.getBaseSalary() * (1 + this.getWorkingHours() / 20);
		}
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
