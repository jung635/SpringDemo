package test1;

//실제 서비스를 하는 클래스
public class KiaMaker {
	
	public Car sell(Money money){
		System.out.println("KiaMaker.sell : I got" + money.getAmount());
		
		Car car = new Car("Pride");
		System.out.println("KiaMaker.sell : I made " + car.getName());
		System.out.println("KiaMaker.sell : I sold " + car.getName());
		
		return car;
	}
	
}
