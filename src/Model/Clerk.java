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
	
	public Double calculateSalary(){
		return this.getBaseSalary() * 5; // calcular salário com base no tempo trabalhado e número de horas;
	}

	public Integer getCounterNumber() {
		return counterNumber;
	}

	public void setCounterNumber(Integer counterNumber) {
		this.counterNumber = counterNumber;
	}
	
}
