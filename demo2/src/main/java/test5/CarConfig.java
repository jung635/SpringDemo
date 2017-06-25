package test5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 *@Configuration처리를 위해 다음 디펜던시 설정이 pom.xml에 필요하다.
 <!-- https://mvnrepository.com/artifact/cglib/cglib -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.2.2</version>
</dependency>
 * */
//스프링에게 설정정보가 있다고 알려준다.
@Configuration
//스캔을 통해서 CarConfig클래스를 빈으로 등록
@Component
public class CarConfig {
	
	//빈 id를 지정해서 사용한다.
	@Bean(name="hyundai")
	public CarMaker hyundaiMaker(){
		CarMaker maker = new HyundaiMaker();
		return maker;
	}
	
	//id를 생략하면 메소드명을 id로 사용한다.
	@Bean
	public CarMaker kiaMotors(){
		CarMaker maker = new KiaMaker();
		return maker;
	}
	
	//@PostConstruct 애너테이션을 해당 메소드에 설정해서 사용해도 된다.
	@Bean(initMethod="init")
	public OrderManager orderManager(){
		OrderManager manager = new OrderManager(kiaMotors());
		return manager;
	}
}
