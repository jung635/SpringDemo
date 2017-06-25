package test5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/*
 *@Configurationó���� ���� ���� ������� ������ pom.xml�� �ʿ��ϴ�.
 <!-- https://mvnrepository.com/artifact/cglib/cglib -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.2.2</version>
</dependency>
 * */
//���������� ���������� �ִٰ� �˷��ش�.
@Configuration
//��ĵ�� ���ؼ� CarConfigŬ������ ������ ���
@Component
public class CarConfig {
	
	//�� id�� �����ؼ� ����Ѵ�.
	@Bean(name="hyundai")
	public CarMaker hyundaiMaker(){
		CarMaker maker = new HyundaiMaker();
		return maker;
	}
	
	//id�� �����ϸ� �޼ҵ���� id�� ����Ѵ�.
	@Bean
	public CarMaker kiaMotors(){
		CarMaker maker = new KiaMaker();
		return maker;
	}
	
	//@PostConstruct �ֳ����̼��� �ش� �޼ҵ忡 �����ؼ� ����ص� �ȴ�.
	@Bean(initMethod="init")
	public OrderManager orderManager(){
		OrderManager manager = new OrderManager(kiaMotors());
		return manager;
	}
}
