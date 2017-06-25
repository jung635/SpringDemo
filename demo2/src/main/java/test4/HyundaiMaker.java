package test4;

import org.springframework.stereotype.Component;

/*
 <bean id="hyundai" class="test3.HyundaiMaker"></bean>
 이에 대응하는 annotation이 Component
 annotaion밑에 class이름이 있기 때문에 id만 적어주면 됨.
 */
//Component가 빈에 등록하는 아이
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
