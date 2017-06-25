package test4;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//@Component�� ���� java annotation
@Named
public class TestClient {
	
	@Inject
	OrderManager orderManager;
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath�� �������� xml�� ã�´�
		ApplicationContext context = new ClassPathXmlApplicationContext("car-config-annotaion.xml");
		
		//OrderManager���� @Service������̼��� �پ��ִ�. id�� �������� ������ Ŭ�����̸��� id�� ����ϹǷ� orderManager
		OrderManager manager = context.getBean("orderManager", OrderManager.class);
		System.out.println("manager" + manager);
		
		manager.order();
		
		System.out.println("====================");
		
		TestClient testClient = context.getBean("testClient", TestClient.class);
		testClient.orderManager.order();
		
		System.out.println("====================");
		
		System.out.println(manager);
		System.out.println(testClient.orderManager);
		System.out.println(context.getBean("orderManager", OrderManager.class));
	}

}
