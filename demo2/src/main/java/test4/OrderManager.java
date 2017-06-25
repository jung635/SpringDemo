package test4;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//클래스의 용도를 구분하기 쉽게 하기 위해서 @Component를 확장해서 새로운 annotation을 만든 것.
//즉, @Service도 @Componenet처럼 작동해서 bean으로 등록함.
//id를 지정하지 않으면 클래스이름을 id로 사용한다. 단 id는 소문자로 시작한다.
@Service
public class OrderManager {
	
	@Inject
	@Named("hyundai")
	//CarMaker 자료형이 두개가 있음 HyundaiMaker, KiaMaker
	//Named("hyundai") 범위를 좁혀줌
	private CarMaker maker;
	
	//자료형으로 찾아서 주입
	@Autowired //필드변수, 생성자, 메소드에 사용이 가능하다.
	//CarMaker 자료형이 두개가 있음 HyundaiMaker, KiaMaker
	//@Qualifier("hyundai") 범위를 좁혀줌
	public OrderManager(@Qualifier("hyundai") CarMaker maker) {
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
	
	
	//아이디로 찾아서 주입. 생성자에 사용이 불가하다.
	@Resource(name="kia")
	public void setMaker(CarMaker maker) {
		this.maker = maker;
	}
	
	@PostConstruct
	public void init(){
		//객체가 빈으로 등록된 다음에 초기화 작업을 위한 메소드
		System.out.println("OrderManager.Init() called");
	}
	
}
