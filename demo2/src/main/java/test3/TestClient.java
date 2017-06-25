package test3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath�� �������� xml�� ã�´�
		ApplicationContext context = new ClassPathXmlApplicationContext("car-config.xml");
		
		
		OrderManager manager = context.getBean("manager", OrderManager.class);
		System.out.println("manager" + manager);
		
		manager.order();
	}

}
