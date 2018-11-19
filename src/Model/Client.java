package Model;

public class Client extends Person {

	private Double valueSpent;
	
	public Client() {
		
	}

	public Client(Integer id, String name, String phone, Double valueSpent) {
		super(id, name, phone);
		this.valueSpent = valueSpent;
	}

	public Double getValueSpent() {
		return valueSpent;
	}

	public void setValueSpent(Double valueSpent) {
		this.valueSpent = valueSpent;
	}

	public Integer calculatePoints() {
		
		if (this.valueSpent > 1000){
			return new Integer((int) Math.round(this.valueSpent * 50));
		}
		return new Integer((int) Math.round(this.valueSpent * 35));
	}
}
