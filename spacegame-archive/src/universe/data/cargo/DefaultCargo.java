package universe.data.cargo;

import universe.data.Cargo;

public abstract class DefaultCargo implements Cargo {

	double amount;
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public abstract String getName();

}
