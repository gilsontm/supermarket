package Model;

public class Clerk extends Employee {
	
	private Integer counterNumber;
	
	public Clerk() {
		
	}

	public Clerk(Integer id, String name, String phone, Double baseSalary,
			Integer timeWorked, Integer workingHours, Integer counterNumber) {
		super(id, name, phone, baseSalary, timeWorked, workingHours);
		this.counterNumber = counterNumber;
	}
	
	// calcular salário com base no tempo trabalhado e número de horas;
	public Double calculateSalary(){
		if (this.getTimeWorked() > 3) {
			return (100 * this.getTimeWorked()) + (this.getBaseSalary() * (0.8 + this.getWorkingHours() / 20));
		} else {
			return this.getBaseSalary() * (0.8 + this.getWorkingHours() / 20);
		}
	}

	public Integer getCounterNumber() {
		return counterNumber;
	}

	public void setCounterNumber(Integer counterNumber) {
		this.counterNumber = counterNumber;
	}
	
}
