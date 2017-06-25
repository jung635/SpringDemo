package test1;
public class OrderManager {

//	private HyundaiMaker maker;
	private KiaMaker maker;
	
	public OrderManager(){
		//maker = new HyundaiMaker();
		maker = new KiaMaker();
	}
	
	public void order(){
		Money money = new Money(1000);
		System.out.println("OrderManager.order : I made " + money.getAmount());

		System.out.println("OrderManager.order : I payed " + money.getAmount());
		
		Car car = maker.sell(money);
		System.out.println("OrderManager.order : I recieved " + car.getName());
	
	}
}
