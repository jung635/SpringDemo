package test5;

//실제 서비스를 하는 클래스
public class HyundaiMaker implements CarMaker{
	
	@Override
	public Car sell(Money money){
		System.out.println("HyundaiMaker.sell : I got" + money.getAmount());
		
		Car car = new Car("Sonata");
		System.out.println("HyundaiMaker.sell : I made " + car.getName());
		System.out.println("HyundaiMaker.sell : I sold " + car.getName());
		
		return car;
	}
	
}
