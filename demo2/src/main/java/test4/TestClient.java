package test4;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//@Component랑 같은 java annotation
@Named
public class TestClient {
	
	@Inject
	OrderManager orderManager;
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath를 기준으로 xml을 찾는다
		ApplicationContext context = new ClassPathXmlApplicationContext("car-config-annotaion.xml");
		
		//OrderManager에는 @Service어노테이션이 붙어있다. id를 지정하지 않으면 클래스이름을 id로 사용하므로 orderManager
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
