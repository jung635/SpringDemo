package test4;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Ŭ������ �뵵�� �����ϱ� ���� �ϱ� ���ؼ� @Component�� Ȯ���ؼ� ���ο� annotation�� ���� ��.
//��, @Service�� @Componenetó�� �۵��ؼ� bean���� �����.
//id�� �������� ������ Ŭ�����̸��� id�� ����Ѵ�. �� id�� �ҹ��ڷ� �����Ѵ�.
@Service
public class OrderManager {
	
	@Inject
	@Named("hyundai")
	//CarMaker �ڷ����� �ΰ��� ���� HyundaiMaker, KiaMaker
	//Named("hyundai") ������ ������
	private CarMaker maker;
	
	//�ڷ������� ã�Ƽ� ����
	@Autowired //�ʵ庯��, ������, �޼ҵ忡 ����� �����ϴ�.
	//CarMaker �ڷ����� �ΰ��� ���� HyundaiMaker, KiaMaker
	//@Qualifier("hyundai") ������ ������
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
	
	
	//���̵�� ã�Ƽ� ����. �����ڿ� ����� �Ұ��ϴ�.
	@Resource(name="kia")
	public void setMaker(CarMaker maker) {
		this.maker = maker;
	}
	
	@PostConstruct
	public void init(){
		//��ü�� ������ ��ϵ� ������ �ʱ�ȭ �۾��� ���� �޼ҵ�
		System.out.println("OrderManager.Init() called");
	}
	
}
