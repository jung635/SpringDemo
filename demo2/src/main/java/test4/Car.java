package test4;

//Mutable Class : 상태 값이 변경되는 클래스. 일반적으로 빈에 등록하지 않고 사용
public class Car {
	private String name;
	
	public Car(){}
	
	public Car(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
