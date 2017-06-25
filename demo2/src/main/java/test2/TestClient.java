package test2;

public class TestClient {
	
	public static void main(String[] args){
		CarMaker maker = new HyundaiMaker();
		
		OrderManager manager = new OrderManager();
		manager.setMaker(maker);
		manager.order();
	}

}
