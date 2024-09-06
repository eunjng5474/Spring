package polymorphism3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// Spring 컨테이너를 생성한다. 
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// Spring 컨테이너로부터 사용할 객체를 획득(Lookup)한다. 		
		TV tv = (TV) container.getBean("tv");
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();		
		tv.powerOff();
		
		// Spring 컨테이너를 종료한다. 
		container.close();
	}

}
