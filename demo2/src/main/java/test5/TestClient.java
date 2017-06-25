package test5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClient {
	
	public static void main(String[] args){
		
		//ClassPathXmlApplicationContext : classPath를 기준으로 xml을 찾는다
		ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);
		//OrderManager에는 @Service어노테이션이 붙어있다. id를 지정하지 않으면 클래스이름을 id로 사용하므로 orderManager
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
