package test3;
public class OrderManager {

	private CarMaker maker;
	
	public OrderManager(CarMaker maker) {
		this.maker = maker;
		System.out.println("OrderManager() called");
	}

	public void order(){
		Money money = new Money(1000);
		System.out.println("OrderManager.order : I made " + money.getAmount());

		System.out.println("OrderManager.order : I payed " + money.getAmount());
		
		Car car = maker.sell(money);
		System.out.println("OrderManager.order : I recieved " + car.getName());
	
	}	

	public void setMaker(CarMaker maker) {
		this.maker = maker;
	}
	
	public void init(){
		//객체가 빈으로 등록된 다음에 초기화 작업을 위한 메소드
		System.out.println("OrderManager.Init() called");
	}
	
}
