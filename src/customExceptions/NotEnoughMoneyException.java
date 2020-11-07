package customExceptions;

public class NotEnoughMoneyException extends Exception{
	
	private double money;
	private static final long serialVersionUID = 1L;

	public NotEnoughMoneyException(double money) {
		super("Not enough money to do this transaction, you only have: "+money);
		this.money = money;
	}
	
	public double getMoney() {
		return money;
	}

}
