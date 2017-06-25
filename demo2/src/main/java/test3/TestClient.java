package test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath를 기준으로 xml을 찾는다
		ApplicationContext context = new ClassPathXmlApplicationContext("car-config.xml");
		
		
		OrderManager manager = context.getBean("manager", OrderManager.class);
		System.out.println("manager" + manager);
		
		manager.order();
	}

}
