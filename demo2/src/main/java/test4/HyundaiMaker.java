package test4;

import org.springframework.stereotype.Component;

/*
 <bean id="hyundai" class="test3.HyundaiMaker"></bean>
 �̿� �����ϴ� annotation�� Component
 annotaion�ؿ� class�̸��� �ֱ� ������ id�� �����ָ� ��.
 */
//Component�� �� ����ϴ� ����
@Component("hyundai")
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
