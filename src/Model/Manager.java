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
	
	@Override
	public Double calculateSalary(){
		return this.getBaseSalary() * 5; // calcular salário com base no tempo trabalhado e número de horas;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
