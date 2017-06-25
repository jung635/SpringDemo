package test5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath�� �������� xml�� ã�´�
		ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);
		//OrderManager���� @Service������̼��� �پ��ִ�. id�� �������� ������ Ŭ�����̸��� id�� ����ϹǷ� orderManager
		OrderManager manager = context.getBean("orderManager", OrderManager.class);
		System.out.println("manager" + manager);
		
		manager.order();
		
		System.out.println("====================");
		
		System.out.println(manager == context.getBean("orderManager", OrderManager.class));
		
		
		System.out.println("====================");
		
		System.out.println(context.getBean("hyundai", HyundaiMaker.class));
		System.out.println(context.getBean("kiaMotors", KiaMaker.class));
	}

}
