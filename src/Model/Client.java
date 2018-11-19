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

	// calcula os pontos (de uma suposta promoção para clientes) de acordo com o valor gasto pelo cliente
	public Integer calculatePoints() {
		if (this.valueSpent > 1000){
			return new Integer((int) Math.round(this.valueSpent * 20));
		}
		return new Integer((int) Math.round(this.valueSpent * 15));
	}
}
