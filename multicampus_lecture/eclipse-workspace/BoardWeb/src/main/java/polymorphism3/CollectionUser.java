package polymorphism3;

import java.util.Properties;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionUser {

	public static void main(String[] args) {
		// Spring 컨테이너를 생성한다. 
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// Spring 컨테이너로부터 사용할 객체를 획득(Lookup)한다. 		
		CollectionBean bean = (CollectionBean) container.getBean("collection");
		Properties memberList = bean.getAddressList();
		
		System.out.println("[ 주소 목록 ]");
		for (Object address : memberList.values()) {
			System.out.println("---> " + address.toString());	
		}
		
		System.out.println("[ 회원 목록 ]");
		for (Object name : memberList.keySet()) {
			System.out.println("---> " + name.toString());	
		}
		
		// Spring 컨테이너를 종료한다. 
		container.close();
	}

}
