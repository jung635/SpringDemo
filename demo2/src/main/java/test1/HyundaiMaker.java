package test1;

//���� ���񽺸� �ϴ� Ŭ����
public class HyundaiMaker {
	
	public Car sell(Money money){
		System.out.println("HyundaiMaker.sell : I got" + money.getAmount());
		
		Car car = new Car("Sonata");
		System.out.println("HyundaiMaker.sell : I made " + car.getName());
		System.out.println("HyundaiMaker.sell : I sold " + car.getName());
		
		return car;
	}
	
}
