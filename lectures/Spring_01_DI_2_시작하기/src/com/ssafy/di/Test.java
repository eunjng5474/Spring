package com.ssafy.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		
		// 스프링 컨테이너 객체를 빌드 
		// new GXAC 자동완성 
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 컨테이너로부터 객체를 가져오겠다 
		// 반환값이 object 형태이기 때문에 형변환 해줘야 한다 
		Programmer p = (Programmer) context.getBean("programmer");
		Desktop desktop = context.getBean("desktop", Desktop.class);
		
		p.setComputer(desktop);
		p.coding();
		
		
		Desktop d = context.getBean("desktop", Desktop.class);
		Desktop d2 = context.getBean("desktop", Desktop.class);
		
		System.out.println(d == d2);   // true
	}
}
